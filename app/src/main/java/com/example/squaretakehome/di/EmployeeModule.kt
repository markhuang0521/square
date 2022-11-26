package com.example.squaretakehome.di

import com.example.squaretakehome.employee.EMPLOYEE_BASE_URL
import com.example.squaretakehome.employee.EmployeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object EmployeeModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(EMPLOYEE_BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService = retrofit.create(EmployeeService::class.java)

    @Singleton
    @Provides
    fun providesRepository(employeeService: EmployeeService) = Repository(apiService)
}