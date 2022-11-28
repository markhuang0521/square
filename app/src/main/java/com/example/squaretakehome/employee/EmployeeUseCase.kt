package com.example.squaretakehome.employee

import com.example.squaretakehome.employee.model.EmployeeResponse
import com.example.squaretakehome.util.Lce
import javax.inject.Inject

class EmployeeUseCase @Inject constructor(private val repository: EmployeeRepository) {

    suspend fun getEmployees():Lce<List<EmployeeResponse>> = repository.getEmployees().map(
            successBlock = { result-> Lce.Content(data = result.employees)},
            errorBlock = {exception-> Lce.Error(exception = exception)}
        )



}