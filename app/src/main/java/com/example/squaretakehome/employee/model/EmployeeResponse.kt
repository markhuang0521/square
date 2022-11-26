package com.example.squaretakehome.employee.model

import com.squareup.moshi.Json

data class EmployeeResponse (
    @field:Json(name = "uuid") val uuid :String,
    @field:Json(name = "full_name") val fullName :String,
    @field:Json(name = "phone_number") val phoneNumber :String,
    @field:Json(name = "email_address") val emailAddress :String,
    @field:Json(name = "biography") val biography :String,
    @field:Json(name = "photo_url_small") val photoUrlSmall :String,
    @field:Json(name = "photo_url_large") val photoUrlLarge :String,
    @field:Json(name = "team") val team :String,
    @field:Json(name = "employee_type") val employeeType :EmployeeType,
        )

enum class EmployeeType {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR
}
