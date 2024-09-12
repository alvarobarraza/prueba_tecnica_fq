package com.example.technical_test_fq.domain.useCases

import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.ProjectRepository
import javax.inject.Inject

class GetAllProjectsUseCase @Inject constructor(private val projectRepository: ProjectRepository){

    suspend operator fun invoke(): ResultEvent<List<ResultProjectApi>> {
        return projectRepository.getAllProjects()
    }

}