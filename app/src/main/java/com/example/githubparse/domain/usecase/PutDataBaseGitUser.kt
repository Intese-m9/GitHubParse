package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.repository.RepositoryDataBaseDownloadList
import com.example.githubparse.domain.models.GitHubItem
import javax.inject.Inject

class PutDataBaseGitUser @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBaseDownloadList) {
    suspend fun insertGitUser(user: GitHubItem) {
        apiRepositoryDataBase.addUser(user)
    }
}