package com.example.technical_test_fq.di

import android.content.Context
import androidx.room.Room
import com.example.technical_test_fq.data.local.dao.ProjectDao
import com.example.technical_test_fq.data.local.dao.UserDao
import com.example.technical_test_fq.data.local.db.ProjectDatabase
import com.example.technical_test_fq.data.local.db.UserDatabase
import com.example.technical_test_fq.data.repository.ProjectDBRepositoryImpl
import com.example.technical_test_fq.data.repository.UserDbRepositoryImpl
import com.example.technical_test_fq.domain.repository.ProjectDBRepository
import com.example.technical_test_fq.domain.repository.UserDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "auth_db"
        ).build()
    }

    @Provides
    fun provideUserRegisterDao(db: UserDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideAuthRepository(userDao: UserDao): UserDBRepository {
        return UserDbRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideProjectDatabase(@ApplicationContext appContext: Context): ProjectDatabase {
        return Room.databaseBuilder(
            appContext,
            ProjectDatabase::class.java,
            "project_db"
        ).build()
    }

    @Provides
    fun provideResultProjectDao(db: ProjectDatabase): ProjectDao {
        return db.projectDao()
    }

    @Provides
    fun provideResultProjectRepository(resultProjectDao: ProjectDao): ProjectDBRepository {
        return ProjectDBRepositoryImpl(resultProjectDao)
    }



}