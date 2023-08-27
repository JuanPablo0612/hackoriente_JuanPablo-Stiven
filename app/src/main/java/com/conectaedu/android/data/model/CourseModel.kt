package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.Course

data class CourseModel(
    val id: String,
    val name: String,
    val description: String
) {
    constructor() : this(id = "", name = "", description = "")
}

fun CourseModel.toDomain() = Course(
    id = id,
    name = name,
    description = description
)