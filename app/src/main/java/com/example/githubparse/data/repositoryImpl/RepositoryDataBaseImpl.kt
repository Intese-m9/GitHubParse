package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.domain.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.utils.mapper.toDomain
import com.example.githubparse.presentation.utils.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryDataBaseImpl @Inject constructor(private val gitDao: GitDao) : RepositoryDataBase {
    override suspend fun executeDataBase(): Flow<List<GitHubItem>> {
        return gitDao.getAllRepos().map { users ->
            users.map { it.toDomain() }
        }
    }

    override suspend fun addUser(user: GitHubItem) {
        gitDao.insertRepo(repo = user.toEntity())
    }

    override suspend fun deleteUser(user: String) {
        gitDao.deleteCurrentUser(repo = user)
    }
}

