package com.conectaedu.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.data.model.data
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.areas.GetAllAreasUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAllAreasUseCase: GetAllAreasUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { user ->
                getAllAreasUseCase().collect { areas ->
                    val registeredAreas =
                        areas.data().filter { user.data().registeredAreaIds.contains(it.id) }

                    uiState = uiState.copy(currentUser = user.data(), registeredAreas = registeredAreas)
                }
            }
        }
    }
}

data class HomeUiState(
    val currentUser: User = User(),
    val registeredAreas: List<Area> = emptyList()
)