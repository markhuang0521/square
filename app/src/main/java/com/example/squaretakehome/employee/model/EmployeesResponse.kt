package com.example.squaretakehome.employee.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeesResponse(
    @Json(name = "employees") val employees: List<EmployeeResponse>
)