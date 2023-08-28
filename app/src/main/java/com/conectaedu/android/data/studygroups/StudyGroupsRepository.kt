package com.conectaedu.android.data.studygroups

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.data.studygroups.remote.StudyGroupsRemoteDataSource
import com.conectaedu.android.domain.model.StudyGroup
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudyGroupsRepository @Inject constructor(private val studyGroupsRemoteDataSource: StudyGroupsRemoteDataSource) {
    fun getAllByCourse(areaId: String, courseId: String): Flow<Result<List<StudyGroup>>> =
        studyGroupsRemoteDataSource.getAllByCourse(areaId, courseId)
            .map { Result.Success(it.map { studyGroupModel -> studyGroupModel.toDomain() }) }
            .catch { Result.Error(it as Exception) }

    fun getById(areaId: String, courseId: String, studyGroupId: String): Flow<Result<StudyGroup>> =
        studyGroupsRemoteDataSource.getById(areaId, courseId, studyGroupId)
            .map { Result.Success(it.toDomain()) }
            .catch { Result.Error(it as Exception) }

    suspend fun add(areaId: String, courseId: String, studyGroup: StudyGroup): Result<Nothing> {
        val studyGroupModel = studyGroup.toModel()
        return studyGroupsRemoteDataSource.add(areaId, courseId, studyGroupModel)
    }
}