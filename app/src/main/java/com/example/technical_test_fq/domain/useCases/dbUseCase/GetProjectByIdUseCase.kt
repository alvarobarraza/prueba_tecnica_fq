package com.example.technical_test_fq.domain.useCases.dbUseCase

import com.example.technical_test_fq.data.local.entities.ProjectEntity
import com.example.technical_test_fq.domain.repository.ProjectDBRepository
import javax.inject.Inject

class GetProjectByIdUseCase @Inject constructor(private val projectDBRepository: ProjectDBRepository) {

    suspend operator fun invoke(id: Int): ProjectEntity?{
        return projectDBRepository.getProjectById(id)
    }

}