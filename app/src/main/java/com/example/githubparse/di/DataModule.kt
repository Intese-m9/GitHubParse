package com.example.githubparse.di

import android.content.Context
import androidx.room.Room
import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.repositoryImpl.RepositoryDataBaseImpl
import com.example.githubparse.data.repositoryImpl.RepositoryNetImpl
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideGitDataBase(@ApplicationContext context:Context):GitDatabase{
        return Room.databaseBuilder(context, GitDatabase::class.java,"repository_database").build()
    }

    @Provides
    @Singleton
    fun provideRepo(): RepositoryNet {
        return RepositoryNetImpl()
    }

    @Provides
    @Singleton
    fun provideDataBaseRepo(gitDao: GitDao):RepositoryDataBase{
        return RepositoryDataBaseImpl(gitDao)
    }

    @Provides
    fun provideGitDao(gitDatabase: GitDatabase): GitDao {
        return gitDatabase.GitDao() // Получение GitDao из базы данных
    }
}

