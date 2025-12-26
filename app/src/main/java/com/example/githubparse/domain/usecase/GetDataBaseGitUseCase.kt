package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.repository.RepositoryDataBaseDownloadList
import com.example.githubparse.domain.models.GitHubItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataBaseGitUseCase @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBaseDownloadList) {
    suspend fun getDownloadListGit(): Flow<List<GitHubItem>> {
        return apiRepositoryDataBase.executeDataBase()
    }
}