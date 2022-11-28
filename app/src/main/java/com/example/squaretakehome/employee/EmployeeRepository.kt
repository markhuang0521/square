package com.example.squaretakehome.employee

import com.example.squaretakehome.util.BaseRepo
import com.example.squaretakehome.employee.model.EmployeesResponse
import com.example.squaretakehome.util.NetworkResult
import javax.inject.Inject

class EmployeeRepository @Inject constructor( private val employeeService: EmployeeService): BaseRepo() {


    suspend fun getEmployees() :NetworkResult<EmployeesResponse> = safeApiCall { employeeService.getEmployeesSuccess() }

}