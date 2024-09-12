package com.example.technical_test_fq.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.technical_test_fq.data.local.dao.ProjectDao
import com.example.technical_test_fq.data.local.entities.ProjectEntity

@Database(entities = [ProjectEntity::class], version = 1, exportSchema = false)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}