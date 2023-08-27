package com.conectaedu.android.data.studygroups

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.studygroups.remote.StudyGroupsRemoteDataSource
import com.conectaedu.android.domain.model.StudyGroup
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudyGroupsRepository @Inject constructor(private val studyGroupsRemoteDataSource: StudyGroupsRemoteDataSource) {
    fun getAllByCourse(areaId: String, courseId: String) =
        studyGroupsRemoteDataSource.getAllByCourse(areaId, courseId)
            .map { Result.Success(it) }
            .catch { Result.Error(it as Exception) }

    suspend fun add(areaId: String, courseId: String, studyGroup: StudyGroup): Result<Nothing> {
        val studyGroupModel = studyGroup.toModel()
        return studyGroupsRemoteDataSource.add(areaId, courseId, studyGroupModel)
    }
}