package com.conectaedu.android.domain.usecase.messages

import com.conectaedu.android.data.messages.MessagesRepository
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.domain.model.Message
import javax.inject.Inject

class AddMessageUseCase @Inject constructor(private val messagesRepository: MessagesRepository) {
    suspend operator fun invoke(
        areaId: String,
        courseId: String,
        studyGroupId: String,
        senderId: String,
        text: String,
        imageUrl: String
    ): Result<Nothing> {
        val message = Message(senderId = senderId, text = text, imageUrl = imageUrl)

        return messagesRepository.add(areaId, courseId, studyGroupId, message)
    }
}