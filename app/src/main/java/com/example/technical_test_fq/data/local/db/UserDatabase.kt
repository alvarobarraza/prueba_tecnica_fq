package com.example.technical_test_fq.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.technical_test_fq.data.local.dao.UserDao
import com.example.technical_test_fq.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}