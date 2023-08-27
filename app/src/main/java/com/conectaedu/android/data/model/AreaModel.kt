package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.Area

data class AreaModel(
    val id: String = "",
    val name: String = "",
    val courseIds: List<String> = emptyList()
)

fun AreaModel.toDomain() = Area(id = id, name = name, courseIds = courseIds)