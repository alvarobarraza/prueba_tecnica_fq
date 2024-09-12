package com.example.technical_test_fq.domain.repository

import com.example.technical_test_fq.domain.model.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getToken(): Token?

    fun deleteToken()

}