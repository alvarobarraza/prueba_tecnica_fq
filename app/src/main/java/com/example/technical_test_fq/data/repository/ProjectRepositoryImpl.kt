package com.example.technical_test_fq.data.repository

import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.data.model.SimpleResponse
import com.example.technical_test_fq.data.network.ProjectApi
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(private val projectApi: ProjectApi): ProjectRepository{

    override suspend fun getAllProjects(): ResultEvent<List<ResultProjectApi>> {
        return try {
            val response = projectApi.getAllProjects()
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

    override suspend fun createProject(body: ProjectRequest): ResultEvent<SimpleResponse> {
        return try {
            val response = projectApi.createProject(body)
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

    override suspend fun updateProject(body: ProjectRequest): ResultEvent<SimpleResponse> {
        return try {
            val response = projectApi.updateProject(body)
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

    override suspend fun deleteProject(id: Int): ResultEvent<SimpleResponse> {
        return try {
            val response = projectApi.deleteProject(id)
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

    override suspend fun projectMetrics(): ResultEvent<ResultProjectMetrics> {
        return try {
            val response = projectApi.projectMetrics()
            if (response.isSuccessful){
                ResultEvent.Success(response.body()!!)
            }else{
                ResultEvent.ErrorCode(response.code())
            }
        }catch (e: Exception){
            ResultEvent.Error(e)
        }
    }

}