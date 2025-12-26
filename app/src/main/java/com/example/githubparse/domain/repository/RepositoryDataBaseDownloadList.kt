package com.example.githubparse.domain.repository

import com.example.githubparse.domain.models.GitHubItem
import kotlinx.coroutines.flow.Flow

interface RepositoryDataBaseDownloadList {
   suspend fun executeDataBase(): Flow<List<GitHubItem>>
   suspend fun addUser(user: GitHubItem)
   suspend fun deleteUser(user: String)
}