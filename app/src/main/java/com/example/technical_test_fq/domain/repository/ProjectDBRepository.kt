package com.example.technical_test_fq.domain.repository

import com.example.technical_test_fq.data.local.entities.ProjectEntity
import com.example.technical_test_fq.data.model.ResultProjectApi

interface ProjectDBRepository {
    suspend fun insertProject(resultProjectApi: ResultProjectApi)
    suspend fun getAllProjects(): List<ProjectEntity>
    suspend fun getProjectById(id: Int): ProjectEntity?
}