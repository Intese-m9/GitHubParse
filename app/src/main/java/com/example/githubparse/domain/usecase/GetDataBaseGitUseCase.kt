package com.example.githubparse.domain.usecase

import android.content.Context
import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitUser
import javax.inject.Inject

class GetDataBaseGitUseCase @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBase) {
    suspend fun getDownloadListGit(context: Context): List<GitUser> {
        return apiRepositoryDataBase.executeDataBase(context)
    }
}