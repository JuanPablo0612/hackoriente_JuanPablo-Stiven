package com.conectaedu.android.data.studygroups.remote

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.StudyGroupModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StudyGroupsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByCourse(areaId: String, courseId: String) =
        firestore.collection("areas/$areaId/courses/$courseId/studyGroups")
            .snapshots()
            .map { it.toObjects<StudyGroupModel>() }

    fun getById(areaId: String, courseId: String, studyGroupId: String) =
        firestore.document("areas/$areaId/courses/$courseId/studyGroups/$studyGroupId").snapshots()
            .map { it.toObject<StudyGroupModel>()!! }

    suspend fun add(
        areaId: String,
        courseId: String,
        studyGroupModel: StudyGroupModel
    ): Result<Nothing> {
        return try {
            val studyGroups = firestore.collection("areas/$areaId/courses/$courseId/studyGroups")
            val id = studyGroups.document().id
            val model = studyGroupModel.copy(id = id)
            studyGroups.document(id).set(model).await()

            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}