package com.example.githubparse.di

import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.domain.usecase.DownloadGitStorage
import com.example.githubparse.domain.usecase.GetCurrentDate
import com.example.githubparse.domain.usecase.OpenGitBrowser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModel {
    @Provides
    fun provideGitBrowser(): OpenGitBrowser {
        return OpenGitBrowser()
    }
    @Provides
    fun provideLoadGitBrowser(): DownloadGitStorage {
        return DownloadGitStorage()
    }
    @Provides
    fun provideDialog(): Dialog {
        return Dialog()
    }
    @Provides
    fun getCurrentDate(): GetCurrentDate{
        return GetCurrentDate()
    }
}