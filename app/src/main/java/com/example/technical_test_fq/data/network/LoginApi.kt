package com.example.technical_test_fq.data.network

import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.data.model.ResultAuthLogin
import com.example.technical_test_fq.data.model.ResultAuthRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    suspend fun authLogin(@Body body: AuthLoginRequest): Response<ResultAuthLogin>

    @POST("auth/register")
    suspend fun authRegister(@Body body: AuthRegisterRequest): Response<ResultAuthRegister>

}