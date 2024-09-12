package com.example.technical_test_fq.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Double,
    val type: String,
    val status: String,
    val description: String
)
