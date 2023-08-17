package com.example.prepaidcardsdk.di

import com.example.prepaidcardsdk.data.repoimpl.RepositoryImplementation
import com.example.prepaidcardsdk.data.src.APIService
import com.example.prepaidcardsdk.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
class RetrofitProider {
    @Provides
    @Singleton
    fun retrofitProvider(): Retrofit {
        return Retrofit.Builder().baseUrl("http://35.200.225.250:8080/cardissuer/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun provideService( retrofit: Retrofit): APIService {
       return retrofit.create(APIService::class.java)
    }
    @Provides
    @Singleton
    fun provideRepo(apiService: APIService): Repository {
        return RepositoryImplementation(apiService)
    }

}