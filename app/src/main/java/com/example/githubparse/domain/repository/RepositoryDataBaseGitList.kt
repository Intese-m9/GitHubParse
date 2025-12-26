package com.example.githubparse.domain.repository

import com.example.githubparse.domain.models.GitHubItem
import kotlinx.coroutines.flow.Flow

interface RepositoryDataBaseGitList {
    suspend fun executeDataBase(): Flow<List<GitHubItem>>
    suspend fun addUser(user: GitHubItem)
}