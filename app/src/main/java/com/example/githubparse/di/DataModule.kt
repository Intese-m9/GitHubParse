package com.example.githubparse.di

import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.repositoryImpl.RepositoryDataBaseImpl
import com.example.githubparse.data.repositoryImpl.RepositoryNetImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRepo(): RepositoryNet {
        return RepositoryNetImpl()
    }

    @Provides
    @Singleton
    fun provideDataBaseRepo():RepositoryDataBase{
        return RepositoryDataBaseImpl()
    }
}

