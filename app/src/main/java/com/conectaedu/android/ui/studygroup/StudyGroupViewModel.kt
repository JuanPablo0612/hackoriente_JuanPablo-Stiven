package com.conectaedu.android.ui.studygroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.data.model.data
import com.conectaedu.android.domain.model.Message
import com.conectaedu.android.domain.model.StudyGroup
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.messages.AddMessageUseCase
import com.conectaedu.android.domain.usecase.messages.GetAllMessagesByStudyGroupUseCase
import com.conectaedu.android.domain.usecase.studygroups.GetStudyGroupByIdUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyGroupViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getStudyGroupByIdUseCase: GetStudyGroupByIdUseCase,
    private val getAllMessagesByStudyGroupUseCase: GetAllMessagesByStudyGroupUseCase,
    private val addMessageUseCase: AddMessageUseCase
) : ViewModel() {
    var uiState by mutableStateOf(StudyGroupUiState())
        private set

    fun init(areaId: String, courseId: String, studyGroupId: String) {
        uiState = uiState.copy(areaId = areaId, courseId = courseId, studyGroupId = studyGroupId)
        getData()
    }

    fun onMessageTextChanged(text: String) {
        uiState = uiState.copy(message = text)
    }

    fun onAddMessage() {
        viewModelScope.launch {
            addMessageUseCase(
                uiState.areaId,
                uiState.courseId,
                uiState.studyGroupId,
                uiState.currentUser.id,
                uiState.message,
                ""
            )
        }
    }

    private fun getData() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { userResult ->
                getStudyGroupByIdUseCase(
                    uiState.areaId,
                    uiState.courseId,
                    uiState.studyGroupId
                ).collect { studyGroupResult ->
                    getAllMessagesByStudyGroupUseCase(
                        uiState.areaId,
                        uiState.courseId,
                        uiState.studyGroupId
                    ).collect { messagesResult ->
                        uiState = uiState.copy(
                            currentUser = userResult.data(),
                            studyGroup = studyGroupResult.data(),
                            messages = messagesResult.data()
                        )
                    }
                }
            }
        }
    }
}

data class StudyGroupUiState(
    val areaId: String = "",
    val courseId: String = "",
    val studyGroupId: String = "",
    val currentUser: User = User(),
    val studyGroup: StudyGroup = StudyGroup(),
    val messages: List<Message> = emptyList(),
    val message: String = ""
)