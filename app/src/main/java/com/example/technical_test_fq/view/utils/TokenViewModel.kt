package com.example.technical_test_fq.view.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.domain.model.Token
import com.example.technical_test_fq.domain.useCases.token.DeleteTokenUseCase
import com.example.technical_test_fq.domain.useCases.token.GetTokenUseCase
import com.example.technical_test_fq.domain.useCases.token.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
) : ViewModel() {
    fun saveToken(token: String) {
        viewModelScope.launch {
            saveTokenUseCase.execute(Token(token))
        }
    }

    fun getToken(): Token? {
        var token: Token? = null
        viewModelScope.launch {
            token = getTokenUseCase.execute()
        }
        return token
    }

    fun deleteToken() {
        viewModelScope.launch {
            deleteTokenUseCase.execute()
        }
    }
}