package com.conectaedu.android.data.courses

import com.conectaedu.android.data.courses.remote.CoursesRemoteDataSource
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Course
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepository @Inject constructor(private val coursesRemoteDataSource: CoursesRemoteDataSource) {
    fun getAllByAreaId(areaId: String) = coursesRemoteDataSource.getAllByAreaId(areaId)
        .map { Result.Success(it.map { courseModel -> courseModel.toDomain() }) }
        .catch { Result.Error(it as Exception) }

    suspend fun add(areaId: String, course: Course): Result<Nothing> {
        val courseModel = course.toModel()
        return coursesRemoteDataSource.add(areaId, courseModel)
    }
}