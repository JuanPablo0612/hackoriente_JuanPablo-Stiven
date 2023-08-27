package com.conectaedu.android.domain.usecase.auth

import com.conectaedu.android.data.auth.AuthRepository
import javax.inject.Inject

class GetCurrentUserIdUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.getCurrentUserId()
}