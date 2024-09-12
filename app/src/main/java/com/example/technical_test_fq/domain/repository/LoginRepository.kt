package com.example.technical_test_fq.domain.repository

import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.data.model.ResultAuthLogin
import com.example.technical_test_fq.data.model.ResultAuthRegister
import com.example.technical_test_fq.domain.model.ResultEvent

interface LoginRepository {

    suspend fun authLogin(body: AuthLoginRequest): ResultEvent<ResultAuthLogin>

    suspend fun authRegister(body: AuthRegisterRequest): ResultEvent<ResultAuthRegister>

}