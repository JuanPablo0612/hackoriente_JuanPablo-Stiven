package com.conectaedu.android.domain.usecase.courses

import com.conectaedu.android.data.courses.CoursesRepository
import javax.inject.Inject

class GetAllCoursesByAreaIdUseCase @Inject constructor(private val coursesRepository: CoursesRepository) {
    operator fun invoke(areaId: String) = coursesRepository.getAllByAreaId(areaId)
}