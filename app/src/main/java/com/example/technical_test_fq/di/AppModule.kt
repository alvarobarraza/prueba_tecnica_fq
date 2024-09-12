package com.example.technical_test_fq.di

import android.content.Context
import android.content.SharedPreferences
import com.example.technical_test_fq.data.network.AuthProjectInterceptor
import com.example.technical_test_fq.data.network.LoginApi
import com.example.technical_test_fq.data.network.ProjectApi
import com.example.technical_test_fq.data.repository.LoginRepositoryImpl
import com.example.technical_test_fq.data.repository.ProjectRepositoryImpl
import com.example.technical_test_fq.data.repository.TokenRepositoryImpl
import com.example.technical_test_fq.domain.repository.LoginRepository
import com.example.technical_test_fq.domain.repository.ProjectRepository
import com.example.technical_test_fq.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val ip = "http://192.168.147.21:3001/"

    /*Init Interceptor*/
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
    /*Finish Interceptor*/

    /*Init Login*/
    @Provides
    @Singleton
    @Named("login")
    fun provideLoginOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginApi(@Named("login") okHttpClient: OkHttpClient): LoginApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ip)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository {
        return LoginRepositoryImpl(loginApi)
    }
    /*Finish Login*/


    /*Init Projects*/
    @Provides
    @Singleton
    @Named("testToken")
    fun provideOkHttpClient(
        authInterceptor: AuthProjectInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideProjectApi(@Named("testToken") okHttpClient: OkHttpClient): ProjectApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ip)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ProjectApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProjectRepository(projectApi: ProjectApi): ProjectRepository {
        return ProjectRepositoryImpl(projectApi)
    }
    /*Finish Projects*/

    /*Init Shared Preference*/
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(sharedPreferences: SharedPreferences): TokenRepository {
        return TokenRepositoryImpl(sharedPreferences)
    }
    /*Finish Shared Preference*/

}