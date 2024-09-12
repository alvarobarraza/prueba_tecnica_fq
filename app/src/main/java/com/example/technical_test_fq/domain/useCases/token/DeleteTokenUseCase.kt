package com.example.technical_test_fq.domain.useCases.token


import com.example.technical_test_fq.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    fun execute() {
        tokenRepository.deleteToken()
    }
}