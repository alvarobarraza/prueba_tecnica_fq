package com.example.technical_test_fq.domain.repository

import com.example.technical_test_fq.data.local.entities.UserEntity
import com.example.technical_test_fq.data.model.AuthRegisterRequest

interface UserDBRepository {

    suspend fun register(user: AuthRegisterRequest)
    suspend fun getUserByEmail(email: String): UserEntity?
    suspend fun getAllUsers(): List<UserEntity>

}