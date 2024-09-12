package com.example.technical_test_fq.domain.useCases.token

import com.example.technical_test_fq.domain.model.Token
import com.example.technical_test_fq.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    fun execute(): Token? {
        return tokenRepository.getToken()
    }
}