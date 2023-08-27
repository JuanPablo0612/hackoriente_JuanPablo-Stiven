package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.MessageModel

data class Message(
    val id: String = "",
    val timestamp: Long = 0,
    val senderId: String = "",
    val text: String = "",
    val imageUrl: String = ""
)

fun Message.toModel() = MessageModel(id = id, senderId = senderId, text = text, imageUrl = imageUrl)