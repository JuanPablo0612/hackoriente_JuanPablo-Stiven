package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.Message
import com.google.firebase.firestore.FieldValue

data class MessageModel(
    val id: String = "",
    val timestamp: FieldValue = FieldValue.serverTimestamp(),
    val senderId: String = "",
    val text: String = "",
    val imageUrl: String = ""
)

fun MessageModel.toDomain() = Message(
    id = id,
    senderId = senderId,
    text = text,
    imageUrl = imageUrl
)