package com.example.technical_test_fq.view.projects.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.useCases.ProjectMetricsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphProjectViewModel @Inject constructor(
    private val projectMetricsUseCase: ProjectMetricsUseCase
) : ViewModel() {

    private val _metricsProjectsData = MutableLiveData<ResultEvent<ResultProjectMetrics>>()
    val metricsProjectsData: LiveData<ResultEvent<ResultProjectMetrics>> get() = _metricsProjectsData

    fun metricsProjects() {
        viewModelScope.launch {
            _metricsProjectsData.value = ResultEvent.Loading
            val result = projectMetricsUseCase()
            _metricsProjectsData.value = result
        }
    }

}