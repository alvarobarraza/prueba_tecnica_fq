package com.example.technical_test_fq.view.projects.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technical_test_fq.R
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.databinding.FragmentListProjectsBinding
import com.example.technical_test_fq.databinding.FragmentLoginBinding
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.view.base.BaseFragment
import com.example.technical_test_fq.view.projects.adapter.ListProjectAdapter
import com.example.technical_test_fq.view.projects.viewModel.ListProjectsViewModel
import com.example.technical_test_fq.view.projects.viewModel.ProjectInfoViewModel
import com.example.technical_test_fq.view.utils.GenericDialogFragment
import com.example.technical_test_fq.view.utils.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment : BaseFragment(), ListProjectAdapter.OnProjectsClickListener, ListProjectAdapter.OnProjectsDeleteClick {

    private lateinit var binding: FragmentListProjectsBinding

    private val listProjectsViewModel: ListProjectsViewModel by viewModels()
    private val projectInfoViewModel: ProjectInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        listProjectsViewModel.getAllProjects()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initViewModel()

    }

    private fun initViewModel() {
        listProjectsViewModel.getAllProjectsData.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ResultEvent.Loading -> {
                    showLoading()
                }

                is ResultEvent.Success -> {
                    hideLoading()
                    for (i in event.data){
                        listProjectsViewModel.insertProject(i)
                    }
                    binding.rvListProjects.adapter = ListProjectAdapter(event.data, this, this)
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

        listProjectsViewModel.deleteProjectsData.observe(viewLifecycleOwner) { event ->
            when (event) {
                is ResultEvent.Loading -> {
                    showLoading()
                }

                is ResultEvent.Success -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Eliminacion Exitosa",
                        "Se ha eliminado correctamente el proyecto",
                        type = GenericDialogFragment.DialogType.SUCCESS,
                        positiveListener = {
                            listProjectsViewModel.getAllProjects()
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

            rvListProjects.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
            }

            btnGra.setOnClickListener {
                findNavController().navigate(R.id.action_listProjectsFragment_to_graphProjectFragment)
            }

            addProject.setOnClickListener {
                projectInfoViewModel.updateOpt(false)
                findNavController().navigate(R.id.action_listProjectsFragment_to_createProjectFragment)
            }


        }
    }

    override fun onProjectClick(project: ResultProjectApi) {
        projectInfoViewModel.updateOpt(true)
        projectInfoViewModel.updateProject(project)
        findNavController().navigate(R.id.action_listProjectsFragment_to_createProjectFragment)
    }

    override fun onProjectsDelete(project: ResultProjectApi) {
        showGenericDialogNotification(
            "¿Estas seguro?",
            "Estas seguro de eliminar el ${project.name}",
            type = GenericDialogFragment.DialogType.ERROR,
            positiveListener = {
                listProjectsViewModel.deleteProjects(project.id)
            }
        )
    }


}