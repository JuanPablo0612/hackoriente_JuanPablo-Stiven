package com.conectaedu.android.data.users

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.data.users.remote.UsersRemoteDataSource
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersRemoteDataSource: UsersRemoteDataSource) {
    suspend fun save(user: User): Result<Nothing> {
        val userModel = user.toModel()
        return usersRemoteDataSource.save(userModel)
    }

    fun get(id: String) = usersRemoteDataSource.get(id)
        .map { userModel -> Result.Success(userModel.toDomain()) }
        .catch { throwable -> Result.Error(throwable as Exception)}

    suspend fun registerArea(user: User, areaId: String): Result<Nothing> {
        val userModel = user.toModel()
        return usersRemoteDataSource.registerArea(userModel, areaId)
    }
}