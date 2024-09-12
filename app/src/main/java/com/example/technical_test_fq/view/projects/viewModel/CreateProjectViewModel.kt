package com.example.technical_test_fq.view.projects.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.useCases.CreateProjectUseCase
import com.example.technical_test_fq.domain.useCases.UpdateProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    private val updateProjectUseCase: UpdateProjectUseCase
) : ViewModel() {

    private val _createProjectsData = MutableLiveData<ResultEvent<SimpleResponse>>()
    val createProjectsData: LiveData<ResultEvent<SimpleResponse>> get() = _createProjectsData

    fun createProjects(body: ProjectRequest) {
        viewModelScope.launch {
            _createProjectsData.value = ResultEvent.Loading
            val result = createProjectUseCase(body)
            _createProjectsData.value = result
        }
    }

    private val _updateProjectsData = MutableLiveData<ResultEvent<SimpleResponse>>()
    val updateProjectsData: LiveData<ResultEvent<SimpleResponse>> get() = _updateProjectsData

    fun updateProjects(body: ProjectRequest) {
        viewModelScope.launch {
            _updateProjectsData.value = ResultEvent.Loading
            val result = updateProjectUseCase(body)
            _updateProjectsData.value = result
        }
    }



}