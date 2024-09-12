package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.ProjectRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(id: Int): ResultEvent<SimpleResponse> {
        return projectRepository.deleteProject(id)
    }

}