package com.example.squaretakehome.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo {

    // we'll use this function in all
    // repos to handle api errors.
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): NetworkResult<T> {

        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {

                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    // when success response and body not nullable we are returning the body
                    // and !! is needed since we cant accept nullable

                    NetworkResult.Success(data = response.body()!!)
                } else {
                    // Simply returning generic exception with nullable response body
                    NetworkResult.Error(exception = Exception("nullable response body"))
                }

            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                NetworkResult.Error(exception = e)
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                NetworkResult.Error(exception = e)
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                NetworkResult.Error(exception = e)
            }
        }
    }
}
