package com.example.technical_test_fq.domain.useCases.dbUseCase

import com.example.technical_test_fq.data.local.entities.UserEntity
import com.example.technical_test_fq.domain.repository.UserDBRepository
import javax.inject.Inject

class GetAllUserDBUseCase @Inject constructor(private val userDBRepository: UserDBRepository) {
    suspend operator fun invoke(): List<UserEntity> {
        return userDBRepository.getAllUsers()
    }
}