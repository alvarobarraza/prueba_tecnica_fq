package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectMetricsUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(): ResultEvent<ResultProjectMetrics> {
        return projectRepository.projectMetrics()
    }

}