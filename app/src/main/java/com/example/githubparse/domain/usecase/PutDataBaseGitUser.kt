package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.repository.RepositoryDataBase
import com.example.githubparse.domain.models.GitHubItem
import javax.inject.Inject

class PutDataBaseGitUser @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBase) {
    suspend fun insertGitUser(user: GitHubItem) {
        apiRepositoryDataBase.addUser(user)
    }
}