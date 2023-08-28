package com.conectaedu.android.domain.usecase.user

import com.conectaedu.android.data.users.UsersRepository
import com.conectaedu.android.domain.model.User
import javax.inject.Inject

class RegisterAreaUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(user: User, areaId: String) =
        usersRepository.registerArea(user, areaId)
}