package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.AreaModel

data class Area(
    val id: String = "",
    val name: String = ""
)

fun Area.toModel() = AreaModel(id = id, name = name)