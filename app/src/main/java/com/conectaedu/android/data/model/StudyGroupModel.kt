package com.conectaedu.android.data.model

data class StudyGroupModel(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val messageIds: List<String> = emptyList()
)
