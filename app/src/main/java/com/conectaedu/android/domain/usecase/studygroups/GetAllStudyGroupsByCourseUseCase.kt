package com.conectaedu.android.domain.usecase.studygroups

import com.conectaedu.android.data.studygroups.StudyGroupsRepository
import javax.inject.Inject

class GetAllStudyGroupsByCourseUseCase @Inject constructor(private val studyGroupsRepository: StudyGroupsRepository) {
    operator fun invoke(areaId: String, courseId: String) =
        studyGroupsRepository.getAllByCourse(areaId, courseId)
}