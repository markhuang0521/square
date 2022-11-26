package com.example.squaretakehome.employee

import BaseRepo
import com.example.squaretakehome.employee.model.EmployeeResponse
import com.example.squaretakehome.util.NetworkResult
import javax.inject.Inject

class EmployeeRepository @Inject constructor( private val employeeService: EmployeeService): BaseRepo() {


    suspend fun getEmployees() :NetworkResult<List<EmployeeResponse>> = safeApiCall { employeeService.getEmployeesSuccess() }

}