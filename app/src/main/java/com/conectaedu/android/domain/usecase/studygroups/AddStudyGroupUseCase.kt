package com.conectaedu.android.domain.usecase.studygroups

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.studygroups.StudyGroupsRepository
import com.conectaedu.android.domain.model.StudyGroup
import javax.inject.Inject

class AddStudyGroupUseCase @Inject constructor(private val studyGroupsRepository: StudyGroupsRepository) {
    suspend operator fun invoke(
        areaId: String,
        courseId: String,
        name: String,
        description: String
    ): Result<Nothing> {
        val studyGroup = StudyGroup(name = name, description = description)
        return studyGroupsRepository.add(areaId, courseId, studyGroup)
    }
}