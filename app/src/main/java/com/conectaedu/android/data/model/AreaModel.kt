package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.Area

data class AreaModel(
    val id: String,
    val name: String
) {
    constructor() : this(id = "", name = "")
}

fun AreaModel.toDomain() = Area(id = id, name = name)