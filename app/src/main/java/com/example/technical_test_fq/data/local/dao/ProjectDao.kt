package com.example.technical_test_fq.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.technical_test_fq.data.local.entities.ProjectEntity

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: ProjectEntity)

    @Query("SELECT * FROM project")
    suspend fun getAllProjects(): List<ProjectEntity>

    @Query("SELECT * FROM project WHERE id = :id")
    suspend fun getProjectById(id: Int): ProjectEntity?

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

}