package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.StudyGroupModel

data class StudyGroup(
    val id: String = "",
    val name: String = "",
    val description: String = ""
)

fun StudyGroup.toModel() = StudyGroupModel(id = id, name = name, description = description)