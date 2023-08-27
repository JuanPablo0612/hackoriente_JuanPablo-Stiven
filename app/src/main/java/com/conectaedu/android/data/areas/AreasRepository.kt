package com.conectaedu.android.data.areas

import com.conectaedu.android.data.areas.remote.AreasRemoteDataSource
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AreasRepository @Inject constructor(private val areasRemoteDataSource: AreasRemoteDataSource) {
    fun getAll() = areasRemoteDataSource.getAll()
        .map { Result.Success(it.map { areaModel -> areaModel.toDomain() }) }
        .catch { Result.Error(it as Exception) }
}