package com.conectaedu.android.data.messages.remote

import com.conectaedu.android.data.model.MessageModel
import com.conectaedu.android.data.model.Result
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessagesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByStudyGroup(areaId: String, courseId: String, studyGroupId: String) =
        firestore.collection("areas/$areaId/courses/$courseId/studyGroups/$studyGroupId/messages")
            .snapshots()
            .map { it.toObjects<MessageModel>() }

    suspend fun add(
        areaId: String,
        courseId: String,
        studyGroupId: String,
        messageModel: MessageModel
    ): Result<Nothing> {
        return try {
            val messages = firestore.collection("areas/$areaId/courses/$courseId/studyGroups/$studyGroupId/messages")
            val id = messages.document().id
            val model = messageModel.copy(id = id)
            messages.document(id).set(model).await()

            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}