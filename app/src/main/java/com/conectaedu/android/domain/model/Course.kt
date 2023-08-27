package com.conectaedu.android.domain.model

data class Course(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val studyGroupIds: List<String> = emptyList()
)
