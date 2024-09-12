package com.example.technical_test_fq.view.projects.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technical_test_fq.R
import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.databinding.FragmentCreateProjectBinding
import com.example.technical_test_fq.databinding.FragmentLoginBinding
import com.example.technical_test_fq.databinding.ItemProjectBinding
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.view.base.BaseFragment
import com.example.technical_test_fq.view.projects.adapter.ListProjectAdapter
import com.example.technical_test_fq.view.projects.viewModel.CreateProjectViewModel
import com.example.technical_test_fq.view.projects.viewModel.ProjectInfoViewModel
import com.example.technical_test_fq.view.utils.GenericDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectFragment : BaseFragment() {

    private lateinit var binding: FragmentCreateProjectBinding

    private val projectInfoViewModel: ProjectInfoViewModel by activityViewModels()
    private val createProjectViewModel: CreateProjectViewModel by viewModels()

    private var optData = false
    private var projectsSelect: ResultProjectApi? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initViewModel()

    }

    private fun initViewModel() {
        projectInfoViewModel.sharedOpt.observe(viewLifecycleOwner) { opt ->
            optData = opt
        }

        projectInfoViewModel.sharedProject.observe(viewLifecycleOwner) { data ->
            projectsSelect = data

            if (optData) {
                with(binding) {
                    edtName.setText(data.name)
                    edtDescription.setText(data.description)
                    edtPrice.setText(data.value.toString())
                    edtType.setText(data.type)

                    createButton.text = "Editar Proyecto"
                }
            }
        }

        createProjectViewModel.createProjectsData.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ResultEvent.Loading -> {
                    showLoading()
                }

                is ResultEvent.Success -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Creacion Exitosa",
                        "Se ha creado correctamente el proyecto",
                        type = GenericDialogFragment.DialogType.SUCCESS,
                        positiveListener = {
                            findNavController().navigateUp()
                        }
                    )
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

        createProjectViewModel.updateProjectsData.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ResultEvent.Loading -> {
                    showLoading()
                }

                is ResultEvent.Success -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Acctualización Exitosa",
                        "Se ha actualizado correctamente el proyecto",
                        type = GenericDialogFragment.DialogType.SUCCESS,
                        positiveListener = {
                            findNavController().navigateUp()
                        }
                    )
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
        with(binding) {

            backPressed()

            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_createProjectFragment_to_listProjectsFragment)
            }

            createButton.setOnClickListener {
                if (edtName.text.isNullOrEmpty() || edtDescription.text.isNullOrEmpty() ||
                    edtPrice.text.isNullOrEmpty() || edtType.text.isNullOrEmpty()
                ) {
                    showGenericDialogNotification(
                        "Campos Incompletos",
                        "Por favor, rellene todos los campos obligatorios antes de continuar.",
                        type = GenericDialogFragment.DialogType.WARNING,
                        positiveListener = {}
                    )
                } else {
                    val body = ProjectRequest(
                        id = 4,
                        name = edtName.text.toString(),
                        value = edtPrice.text.toString().toDouble(),
                        type = edtType.text.toString(),
                        status = "Activo",
                        description = edtDescription.text.toString()
                    )
                    if (optData) {
                        createProjectViewModel.updateProjects(body)
                    } else {
                        createProjectViewModel.createProjects(body)
                    }
                }
            }

        }
    }

    private fun backPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_createProjectFragment_to_listProjectsFragment)
            }
        })
    }


}