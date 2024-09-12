package com.example.technical_test_fq.data.model

data class ProjectRequest (
    val id: Int,
    val name: String,
    val value: Double,
    val type: String,
    val status: String,
    val description: String
)