package com.example.githubparse.di

import android.app.Application
import android.content.Context
import com.example.githubparse.presentation.utils.BoundServiceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class App: Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideBoundServiceUtils(@ApplicationContext context: Context):BoundServiceUtils{
        return BoundServiceUtils(context)
    }
}