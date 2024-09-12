package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.data.model.ResultAuthRegister
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.LoginRepository
import javax.inject.Inject

class AuthRegisterUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(body: AuthRegisterRequest): ResultEvent<ResultAuthRegister> {
        return loginRepository.authRegister(body)
    }

}