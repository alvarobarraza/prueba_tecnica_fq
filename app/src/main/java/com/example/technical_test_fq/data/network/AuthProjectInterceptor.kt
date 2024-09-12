package com.example.technical_test_fq.data.network

import com.example.technical_test_fq.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthProjectInterceptor @Inject constructor(private val tokenRepository: TokenRepository) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = tokenRepository.getToken()

        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer ${token.value}")
        }

        return chain.proceed(requestBuilder.build())
    }
}