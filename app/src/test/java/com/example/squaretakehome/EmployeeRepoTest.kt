package com.example.squaretakehome

import com.example.squaretakehome.employee.EmployeeRepository
import com.example.squaretakehome.employee.EmployeeService
import com.example.squaretakehome.employee.model.EmployeeResponse
import com.example.squaretakehome.employee.model.EmployeesResponse
import com.example.squaretakehome.util.NetworkResult
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class EmployeeRepoTest {

    @MockK
    lateinit var employeeService: EmployeeService

    @MockK
    lateinit var response: Response<EmployeesResponse>
    @MockK
    lateinit var employees: EmployeesResponse
    @MockK
    lateinit var employeeList: List<EmployeeResponse>

    private lateinit var objUnderTest: EmployeeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        objUnderTest = EmployeeRepository(
            employeeService = employeeService
        )
    }
    @Test
    fun `get employee success`() {
        every { response.isSuccessful} returns true
        coEvery { employeeService.getEmployeesSuccess() } returns response
        every { response.body() } returns employees
        every { employees.employees } returns employeeList

        runBlocking {
            val result = objUnderTest.getEmployees()
            assertThat(result).isInstanceOf(NetworkResult.Success::class.java)
            result as NetworkResult.Success
            assertThat(result.data).isEqualTo(employees)
            assertThat(result.data.employees).isEqualTo(employeeList)

        }
    }
}