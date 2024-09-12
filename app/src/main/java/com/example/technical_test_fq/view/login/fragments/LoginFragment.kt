package com.example.technical_test_fq.view.login.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technical_test_fq.R
import com.example.technical_test_fq.data.model.AuthLoginRequest
import com.example.technical_test_fq.databinding.FragmentLoginBinding
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.view.base.BaseFragment
import com.example.technical_test_fq.view.login.viewModel.LoginViewModel
import com.example.technical_test_fq.view.projects.ProjectsActivity
import com.example.technical_test_fq.view.utils.GenericDialogFragment
import com.example.technical_test_fq.view.utils.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initViewModel()

    }

    private fun initListener() {
        with(binding){

            btnRegisterUser.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            loginButton.setOnClickListener {
                if (edtUser.text.isNullOrEmpty() || edtPass.text.isNullOrEmpty()){
                    showGenericDialogNotification(
                        "Campos Incompletos",
                        "Por favor, rellene todos los campos obligatorios antes de continuar.",
                        type = GenericDialogFragment.DialogType.WARNING,
                        positiveListener = {}
                    )
                }else{
                    val body = AuthLoginRequest(
                        email = edtUser.text.toString(),
                        password = edtPass.text.toString()
                    )
                    loginViewModel.authLogin(body)
                }
            }

        }
    }

    private fun initViewModel() {
        loginViewModel.authLoginData.observe(viewLifecycleOwner){ event ->
            when(event){
                is ResultEvent.Loading -> {
                    showLoading()
                }
                is ResultEvent.Success -> {
                    hideLoading()
                    tokenViewModel.saveToken(event.data.token)
                    val intent = Intent(requireContext(), ProjectsActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(requireContext(), "Se inicio sesión correctamente", Toast.LENGTH_LONG).show()
                }
                is ResultEvent.ErrorCode -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Error de Conexión",
                        "Error con el codigo ${event.code}",
                        type = GenericDialogFragment.DialogType.ERROR,
                        positiveListener = {}
                    )
                }
                is ResultEvent.Error -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Error de Conexión",
                        "Error ${event.exception}",
                        type = GenericDialogFragment.DialogType.ERROR,
                        positiveListener = {}
                    )
                }

                else -> {
                    hideLoading()
                }
            }
        }
    }

}