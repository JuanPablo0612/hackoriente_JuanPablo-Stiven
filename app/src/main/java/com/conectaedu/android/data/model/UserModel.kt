package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.User

data class UserModel(
    val id: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val registeredAreaIds: List<String> = emptyList(),
    val registeredStudyGroupIds: List<String> = emptyList(),
    val isAdmin: Boolean = false
) {
    constructor() : this(
        id = "",
        email = "",
        firstName = "",
        lastName = "",
        registeredAreaIds = emptyList(),
        registeredStudyGroupIds = emptyList(),
        isAdmin = false
    )
}

fun UserModel.toDomain() = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    registeredAreaIds = registeredAreaIds,
    registeredStudyGroupIds = registeredStudyGroupIds,
    isAdmin = isAdmin
)