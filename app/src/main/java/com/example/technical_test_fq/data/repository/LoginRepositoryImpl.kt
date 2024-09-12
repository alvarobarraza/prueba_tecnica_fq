package com.example.technical_test_fq.data.repository

import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.data.model.ResultAuthLogin
import com.example.technical_test_fq.data.model.ResultAuthRegister
import com.example.technical_test_fq.data.network.LoginApi
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi): LoginRepository {

    override suspend fun authLogin(body: AuthLoginRequest): ResultEvent<ResultAuthLogin> {
        return try {
            val response = loginApi.authLogin(body)
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

    override suspend fun authRegister(body: AuthRegisterRequest): ResultEvent<ResultAuthRegister> {
        return try {
            val response = loginApi.authRegister(body)
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

}