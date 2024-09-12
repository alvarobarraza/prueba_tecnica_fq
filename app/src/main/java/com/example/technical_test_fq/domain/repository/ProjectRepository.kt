package com.example.technical_test_fq.domain.repository

import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.domain.model.ResultEvent

interface ProjectRepository {

    suspend fun getAllProjects(): ResultEvent<List<ResultProjectApi>>

    suspend fun createProject(body: ProjectRequest): ResultEvent<SimpleResponse>

    suspend fun updateProject(body: ProjectRequest): ResultEvent<SimpleResponse>

    suspend fun deleteProject(id: Int): ResultEvent<SimpleResponse>

    suspend fun projectMetrics(): ResultEvent<ResultProjectMetrics>

}