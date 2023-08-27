package com.conectaedu.android.data.auth

import com.conectaedu.android.data.auth.local.AuthLocalDataSource
import com.conectaedu.android.data.auth.remote.AuthRemoteDataSource
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.data
import com.conectaedu.android.data.model.exception
import com.conectaedu.android.data.model.isSuccess
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) {
    suspend fun getCurrentUserId() = authLocalDataSource.getCurrentUserId().first()

    suspend fun login(email: String, password: String): Result<Nothing> {
        val loginResult = authRemoteDataSource.login(email, password)

        if (!loginResult.isSuccess()) {
            return Result.Error(loginResult.exception())
        }

        val userId = loginResult.data()
        authLocalDataSource.saveUserId(userId)

        return Result.Success(null)
    }

    suspend fun register(email: String, password: String): Result<String> {
        val registerResult = authRemoteDataSource.register(email, password)

        if (!registerResult.isSuccess()) {
            return registerResult
        }

        val userId = registerResult.data()
        authLocalDataSource.saveUserId(userId)

        return Result.Success(userId)
    }
}