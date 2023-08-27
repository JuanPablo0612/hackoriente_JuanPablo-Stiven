package com.conectaedu.android.domain.usecase.areas

import com.conectaedu.android.data.areas.AreasRepository
import javax.inject.Inject

class GetAllAreasUseCase @Inject constructor(private val areasRepository: AreasRepository) {
    operator fun invoke() = areasRepository.getAll()
}