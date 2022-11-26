package com.example.squaretakehome.employee

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    private val _state = mutableStateOf()


}


data class State(
    val id:String,
)