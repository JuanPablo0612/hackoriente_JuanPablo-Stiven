package com.conectaedu.android.data.messages

import com.conectaedu.android.data.messages.remote.MessagesRemoteDataSource
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Message
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessagesRepository @Inject constructor(private val messagesRemoteDataSource: MessagesRemoteDataSource) {
    fun getAllByStudyGroup(areaId: String, courseId: String, studyGroupId: String) =
        messagesRemoteDataSource.getAllByStudyGroup(areaId, courseId, studyGroupId)
            .map { Result.Success(it.map { messageModel -> messageModel.toDomain() }) }
            .catch { Result.Error(it as Exception) }

    suspend fun add(
        areaId: String,
        courseId: String,
        studyGroupId: String,
        message: Message
    ): Result<Nothing> {
        val messageModel = message.toModel()
        return messagesRemoteDataSource.add(areaId, courseId, studyGroupId, messageModel)
    }
}