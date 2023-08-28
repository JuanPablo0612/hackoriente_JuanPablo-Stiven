package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.UserModel

data class User(
    val id: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val registeredAreaIds: List<String> = emptyList(),
    val registeredStudyGroupIds: List<String> = emptyList(),
    val admin: Boolean = false
)

fun User.toModel() = UserModel(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    registeredAreaIds = registeredAreaIds,
    registeredStudyGroupIds = registeredStudyGroupIds,
    admin = admin
)
