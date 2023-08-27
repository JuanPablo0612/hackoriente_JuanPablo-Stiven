package com.conectaedu.android.domain.model

data class Area(
    val id: String = "",
    val name: String = "",
    val courseIds: List<String> = emptyList()
)
