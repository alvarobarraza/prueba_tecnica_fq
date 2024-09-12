package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.ProjectRepository
import javax.inject.Inject

class UpdateProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(body: ProjectRequest): ResultEvent<SimpleResponse> {
        return projectRepository.updateProject(body)
    }

}