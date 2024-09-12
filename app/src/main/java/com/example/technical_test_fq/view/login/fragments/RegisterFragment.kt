package com.example.technical_test_fq.view.login.fragments

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
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.databinding.FragmentLoginBinding
import com.example.technical_test_fq.databinding.FragmentRegisterBinding
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.view.base.BaseFragment
import com.example.technical_test_fq.view.login.viewModel.RegisterViewModel
import com.example.technical_test_fq.view.utils.GenericDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding

    private var data: AuthRegisterRequest? = null

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initViewModel()

    }

    private fun initViewModel() {
        registerViewModel.authRegisterData.observe(viewLifecycleOwner){ event ->
            when(event){
                is ResultEvent.Loading -> {
                    showLoading()
                }
                is ResultEvent.Success -> {
                    hideLoading()
                    findNavController().navigateUp()
                    registerViewModel.registerUser(data!!)
                    Toast.makeText(requireContext(), "Se ha registrado correctamente", Toast.LENGTH_LONG).show()
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

    private fun initListener() {
        with(binding){

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            registerButton.setOnClickListener {
                if (edtUser.text.isNullOrEmpty() || edtPass.text.isNullOrEmpty() || edtName.text.isNullOrEmpty()){
                    showGenericDialogNotification(
                        "Campos Incompletos",
                        "Por favor, rellene todos los campos obligatorios antes de continuar.",
                        type = GenericDialogFragment.DialogType.WARNING,
                        positiveListener = {}
                    )
                }else{
                    val body = AuthRegisterRequest(
                        name = edtName.text.toString(),
                        email = edtUser.text.toString(),
                        password = edtPass.text.toString()
                    )
                    data = body
                    registerViewModel.authRegister(body)
                }
            }

        }
    }

}