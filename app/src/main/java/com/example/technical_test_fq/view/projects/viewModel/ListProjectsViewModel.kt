package com.example.technical_test_fq.view.projects.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.useCases.DeleteProjectUseCase
import com.example.technical_test_fq.domain.useCases.GetAllProjectsUseCase
import com.example.technical_test_fq.domain.useCases.dbUseCase.InsertProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProjectsViewModel @Inject constructor(
    private val getAllProjectsUseCase: GetAllProjectsUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase,
    private val insertProjectUseCase: InsertProjectUseCase,
): ViewModel() {

    private val _getAllProjectsData = MutableLiveData<ResultEvent<List<ResultProjectApi>>>()
    val getAllProjectsData: LiveData<ResultEvent<List<ResultProjectApi>>> get() = _getAllProjectsData

    fun getAllProjects() {
        viewModelScope.launch {
            _getAllProjectsData.value = ResultEvent.Loading
            val result = getAllProjectsUseCase()
            _getAllProjectsData.value = result
        }
    }

    private val _deleteProjectsData = MutableLiveData<ResultEvent<SimpleResponse>>()
    val deleteProjectsData: LiveData<ResultEvent<SimpleResponse>> get() = _deleteProjectsData

    fun deleteProjects(id: Int) {
        viewModelScope.launch {
            _deleteProjectsData.value = ResultEvent.Loading
            val result = deleteProjectUseCase(id)
            _deleteProjectsData.value = result
        }
    }

    fun insertProject(resultProjectApi: ResultProjectApi) = viewModelScope.launch {
        insertProjectUseCase(resultProjectApi)
    }


}