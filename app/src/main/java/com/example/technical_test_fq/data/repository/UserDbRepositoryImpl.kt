package com.example.technical_test_fq.data.repository

import com.example.technical_test_fq.data.local.dao.UserDao
import com.example.technical_test_fq.data.local.entities.UserEntity
import com.example.technical_test_fq.data.model.AuthRegisterRequest
import com.example.technical_test_fq.domain.repository.UserDBRepository
import javax.inject.Inject

class UserDbRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserDBRepository{

    override suspend fun register(user: AuthRegisterRequest) {
        val entity = UserEntity(
            name = user.name,
            email = user.email,
            password = user.password
        )
        userDao.insertUserRegister(entity)
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserRegisterByEmail(email)
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }
}