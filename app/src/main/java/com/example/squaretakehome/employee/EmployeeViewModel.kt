package com.example.squaretakehome.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squaretakehome.employee.model.EmployeeResponse
import com.example.squaretakehome.util.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val useCase: EmployeeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state


    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(employeeList = useCase.getEmployees())
        }
    }

    fun performAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                is Action.RefreshList -> {
                    _state.value = _state.value.copy(isRefreshing = true)
                    _state.value = _state.value.copy(employeeList = useCase.getEmployees())
                    _state.value = _state.value.copy(isRefreshing = false)

                }
            }
        }
    }

}


data class State(
    val employeeList: Lce<List<EmployeeResponse>> = Lce.Loading,
    val isRefreshing: Boolean = false
)

sealed class Action {
    object RefreshList : Action()

}

