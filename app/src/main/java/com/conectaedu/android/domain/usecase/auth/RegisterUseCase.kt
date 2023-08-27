package com.conectaedu.android.domain.usecase.auth

import com.conectaedu.android.data.auth.AuthRepository
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.data
import com.conectaedu.android.data.model.exception
import com.conectaedu.android.data.model.isSuccess
import com.conectaedu.android.data.users.UsersRepository
import com.conectaedu.android.domain.model.User
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): com.conectaedu.android.data.model.Result<Nothing> {
        val registerResult = authRepository.register(email, password)

        if (!registerResult.isSuccess()) {
            return Result.Error(registerResult.exception())
        }

        val userId = registerResult.data()

        val user = User(
            id = userId,
            email = email,
            firstName = firstName,
            lastName = lastName,
            isAdmin = email.contains("admin")
        )

        return usersRepository.save(user)

    }
}