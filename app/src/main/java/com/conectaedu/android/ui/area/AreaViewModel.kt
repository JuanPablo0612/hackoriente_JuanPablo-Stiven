package com.conectaedu.android.ui.area

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.data.model.data
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.Course
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.areas.GetAreaByIdUseCase
import com.conectaedu.android.domain.usecase.courses.AddCourseUseCase
import com.conectaedu.android.domain.usecase.courses.GetAllCoursesByAreaIdUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAreaByIdUseCase: GetAreaByIdUseCase,
    private val getAllCoursesByAreaIdUseCase: GetAllCoursesByAreaIdUseCase,
    private val addCourseUseCase: AddCourseUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AreaUiState())
        private set

    fun init(areaId: String) {
        uiState = uiState.copy(areaId = areaId)
        getData()
    }

    fun onAddCourse(name: String, description: String) {
        viewModelScope.launch {
            addCourseUseCase(uiState.areaId, name, description)
            onShowAddCourseDialogChanged()
        }
    }

    fun onShowAddCourseDialogChanged() {
        uiState = uiState.copy(showAddCourseDialog = !uiState.showAddCourseDialog)
    }

    private fun getData() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { userResult ->
                getAreaByIdUseCase(uiState.areaId).collect { areaResult ->
                    getAllCoursesByAreaIdUseCase(uiState.areaId).collect { coursesResult ->
                        uiState = uiState.copy(
                            currentUser = userResult.data(),
                            area = areaResult.data(),
                            courses = coursesResult.data()
                        )
                    }
                }
            }
        }
    }
}

data class AreaUiState(
    val areaId: String = "",
    val currentUser: User = User(),
    val area: Area = Area(),
    val courses: List<Course> = emptyList(),
    val showAddCourseDialog: Boolean = false
)