package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.data.model.ResultAuthLogin
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.LoginRepository
import javax.inject.Inject

class AuthLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(body: AuthLoginRequest): ResultEvent<ResultAuthLogin>{
        return loginRepository.authLogin(body)
    }

}