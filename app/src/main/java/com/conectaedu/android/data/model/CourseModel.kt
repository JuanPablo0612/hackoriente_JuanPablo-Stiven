package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.StudyGroup

data class CourseModel(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val studyGroupIds: List<String> = emptyList()
)
