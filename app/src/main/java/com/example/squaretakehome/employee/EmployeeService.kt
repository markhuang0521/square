package com.example.squaretakehome.employee

import com.example.squaretakehome.employee.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeService {
    @GET(EMPLOYEE_SUCCESS)
    suspend fun getEmployeesSuccess(): Response<List<EmployeeResponse>>

}
const val EMPLOYEE_BASE_URL= "https://s3.amazonaws.com/sq-mobile-interview/"
private const val EMPLOYEE_SUCCESS = "employees.json"
private const val EMPLOYEE_MALFORMED = "employees_malformed.json"
private const val EMPLOYEE_EMPTY = "employees_empty.json"