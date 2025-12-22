package com.example.githubparse.di

import android.content.Context
import androidx.room.Room
import com.example.githubparse.domain.repository.RepositoryDataBaseDownloadList
import com.example.githubparse.domain.repository.RepositoryNet
import com.example.githubparse.data.repositoryImpl.RepositoryDataBaseDownloadListImpl
import com.example.githubparse.data.repositoryImpl.RepositoryNetImpl
import com.example.githubparse.data.room.GitDownloadDao
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
    fun provideDataBaseRepo(gitDao: GitDownloadDao):RepositoryDataBaseDownloadList{
        return RepositoryDataBaseDownloadListImpl(gitDao)
    }

    @Provides
    fun provideGitDao(gitDatabase: GitDatabase): GitDownloadDao {
        return gitDatabase.GitDownloadDao() // Получение GitDao из базы данных
    }
}

