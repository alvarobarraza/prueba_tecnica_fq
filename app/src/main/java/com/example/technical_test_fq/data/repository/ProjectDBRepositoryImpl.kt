package com.example.technical_test_fq.data.repository

import com.example.technical_test_fq.data.local.dao.ProjectDao
import com.example.technical_test_fq.data.local.entities.ProjectEntity
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.domain.repository.ProjectDBRepository
import javax.inject.Inject

class ProjectDBRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
): ProjectDBRepository {

    override suspend fun insertProject(resultProjectApi: ResultProjectApi) {
        val entity = ProjectEntity(
            id = resultProjectApi.id,
            name = resultProjectApi.name,
            value = resultProjectApi.value,
            type = resultProjectApi.type,
            status = resultProjectApi.status,
            description = resultProjectApi.description
        )
        projectDao.insertProject(entity)
    }

    override suspend fun getAllProjects(): List<ProjectEntity> {
        return projectDao.getAllProjects()
    }

    override suspend fun getProjectById(id: Int): ProjectEntity? {
        return projectDao.getProjectById(id)
    }

}