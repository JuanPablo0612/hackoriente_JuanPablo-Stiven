package com.conectaedu.android.domain.usecase.courses

import com.conectaedu.android.data.courses.CoursesRepository
import com.conectaedu.android.data.model.Result
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.Course
import javax.inject.Inject

class AddCourseUseCase @Inject constructor(private val coursesRepository: CoursesRepository) {
    suspend operator fun invoke(areaId: String, name: String, description: String): Result<Nothing> {
        val course = Course(name = name, description = description)
        return coursesRepository.add(areaId, course)
    }
}