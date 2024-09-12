package com.example.technical_test_fq.domain.useCases.dbUseCase

import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.domain.repository.UserDBRepository
import javax.inject.Inject

class RegisterUserDBUseCase @Inject constructor(private val userDBRepository: UserDBRepository) {
    suspend operator fun invoke(authRegisterRequest: AuthRegisterRequest){
        userDBRepository.register(authRegisterRequest)
    }
}