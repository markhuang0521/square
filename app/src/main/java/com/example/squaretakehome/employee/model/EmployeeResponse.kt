package com.example.squaretakehome.employee.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

// api data class representing the EmployeeResponse normally I would also create Domain class
// for mapping, but this time using the moshi TypeAdapter instead
@JsonClass(generateAdapter = true)
data class EmployeeResponse (
    @Json(name = "uuid") val uuid :String,
    @Json(name = "full_name") val fullName :String,
    @Json(name = "phone_number") val phoneNumber :String,
    @Json(name = "email_address") val emailAddress :String,
    @Json(name = "biography") val biography :String,
    @Json(name = "photo_url_small") val photoUrlSmall :String,
    @Json(name = "photo_url_large") val photoUrlLarge :String,
    @Json(name = "team") val team :String,
    @Json(name = "employee_type") val employeeType :EmployeeType,
        )

enum class EmployeeType(val desc:String) {
    FULL_TIME("full time"),
    PART_TIME ("part time"),
    CONTRACTOR ("contractor")
}

class EmployeeTypeAdapter {
    @ToJson
    fun toJson(type: EmployeeType): String = type.name

    @FromJson
    fun fromJson(value: String): EmployeeType = EmployeeType.valueOf(value)
}
