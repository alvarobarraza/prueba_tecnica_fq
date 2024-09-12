package com.example.technical_test_fq.data.network

import com.example.technical_test_fq.data.model.ProjectRequest
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.data.model.SimpleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProjectApi {

    @GET("projects")
    suspend fun getAllProjects(): Response<List<ResultProjectApi>>

    @POST("projects")
    suspend fun createProject(@Body body: ProjectRequest): Response<SimpleResponse>

    @PUT("projects")
    suspend fun updateProject(@Body body: ProjectRequest): Response<SimpleResponse>

    @DELETE("projects/{id}")
    suspend fun deleteProject(@Path("id") id: Int): Response<SimpleResponse>

    @GET("projects/metrics")
    suspend fun projectMetrics(): Response<ResultProjectMetrics>

}