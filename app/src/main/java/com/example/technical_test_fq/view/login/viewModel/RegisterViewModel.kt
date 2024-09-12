package com.example.technical_test_fq.view.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.data.model.ResultAuthRegister
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.useCases.AuthRegisterUseCase
import com.example.technical_test_fq.domain.useCases.dbUseCase.RegisterUserDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRegisterUseCase: AuthRegisterUseCase,
    private val registerUserDBUseCase: RegisterUserDBUseCase
): ViewModel() {

    private val _authRegisterData = MutableLiveData<ResultEvent<ResultAuthRegister>>()
    val authRegisterData: LiveData<ResultEvent<ResultAuthRegister>> get() = _authRegisterData

    fun authRegister(body: AuthRegisterRequest) {
        viewModelScope.launch {
            _authRegisterData.value = ResultEvent.Loading
            val result = authRegisterUseCase(body)
            _authRegisterData.value = result
        }
    }

    fun registerUser(authRegisterRequest: AuthRegisterRequest) = viewModelScope.launch {
        registerUserDBUseCase(authRegisterRequest)
    }

}