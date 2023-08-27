package com.conectaedu.android.data.courses.remote

import com.conectaedu.android.data.model.CourseModel
import com.conectaedu.android.data.model.Result
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CoursesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByAreaId(areaId: String) = firestore.collection("areas/$areaId/courses").snapshots()
        .map { it.toObjects<CourseModel>() }

    suspend fun add(areaId: String, courseModel: CourseModel): Result<Nothing> {
        return try {
            val courses = firestore.collection("areas/$areaId/courses")
            val id = courses.document().id
            val model = courseModel.copy(id = id)
            courses.document(id).set(model).await()

            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}