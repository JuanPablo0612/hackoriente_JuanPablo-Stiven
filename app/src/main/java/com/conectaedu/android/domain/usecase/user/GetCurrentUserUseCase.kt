package com.conectaedu.android.domain.usecase.user

import com.conectaedu.android.data.auth.AuthRepository
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.users.UsersRepository
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.auth.GetCurrentUserIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Flow<Result<User>> {
        val userId = getCurrentUserIdUseCase()
        return usersRepository.get(userId)
    }
}