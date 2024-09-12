package com.example.technical_test_fq.data.repository

import android.content.SharedPreferences
import com.example.technical_test_fq.domain.model.Token
import com.example.technical_test_fq.domain.repository.TokenRepository

class TokenRepositoryImpl (private val sharedPreferences: SharedPreferences): TokenRepository {

    private val key = "auth_token"

    override fun saveToken(token: Token) {
        sharedPreferences.edit().putString(key, token.value).apply()
    }

    override fun getToken(): Token? {
        val tokenValue = sharedPreferences.getString(key, null)
        return if (tokenValue != null) Token(tokenValue) else null
    }

    override fun deleteToken() {
        sharedPreferences.edit().remove(key).apply()
    }

}