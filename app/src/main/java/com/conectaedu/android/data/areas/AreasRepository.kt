package com.conectaedu.android.data.areas

import com.conectaedu.android.data.areas.remote.AreasRemoteDataSource
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.Course
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AreasRepository @Inject constructor(private val areasRemoteDataSource: AreasRemoteDataSource) {
    fun getAll(): Flow<Result<List<Area>>> = areasRemoteDataSource.getAll()
        .map { Result.Success(it.map { areaModel -> areaModel.toDomain() }) }
        .catch { Result.Error(it as Exception) }

    suspend fun add(area: Area): Result<Nothing> {
        val areaModel = area.toModel()
        return areasRemoteDataSource.add(areaModel)
    }
}