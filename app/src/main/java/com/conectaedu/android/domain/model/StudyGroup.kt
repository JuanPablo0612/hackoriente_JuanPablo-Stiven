package com.conectaedu.android.domain.model

data class StudyGroup(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val messageIds: List<String> = emptyList()
)
