package com.example.technical_test_fq.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.technical_test_fq.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRegister(user: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserRegisterByEmail(email: String): UserEntity?

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<UserEntity>

    @Delete
    suspend fun deleteUserRegister(userRegister: UserEntity)

}