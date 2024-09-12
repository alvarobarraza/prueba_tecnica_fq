package com.example.technical_test_fq.view.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.data.model.ResultAuthLogin
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.useCases.AuthLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authLoginUseCase: AuthLoginUseCase
): ViewModel() {

    private val _authLoginData = MutableLiveData<ResultEvent<ResultAuthLogin>>()
    val authLoginData: LiveData<ResultEvent<ResultAuthLogin>> get() = _authLoginData

    fun authLogin(body: AuthLoginRequest) {
        viewModelScope.launch {
            _authLoginData.value = ResultEvent.Loading
            val result = authLoginUseCase(body)
            _authLoginData.value = result
        }
    }

}