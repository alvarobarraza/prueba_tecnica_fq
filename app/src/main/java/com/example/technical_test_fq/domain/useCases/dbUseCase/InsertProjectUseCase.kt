package com.example.technical_test_fq.domain.useCases.dbUseCase

import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.domain.repository.ProjectDBRepository
import javax.inject.Inject

class InsertProjectUseCase @Inject constructor(private val projectDBRepository: ProjectDBRepository) {

    suspend operator fun invoke(resultProjectApi: ResultProjectApi){
        projectDBRepository.insertProject(resultProjectApi)
    }

}