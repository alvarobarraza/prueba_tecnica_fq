package com.example.technical_test_fq.data.model

data class ResultAuthLogin (
    val token: String,
    val user: User
)

data class User (
    val id: Int,
    val name: String,
    val email: String
)

