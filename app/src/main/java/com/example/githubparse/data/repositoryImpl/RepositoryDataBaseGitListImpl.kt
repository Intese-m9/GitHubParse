package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.data.room.GitListDao
import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.domain.repository.RepositoryDataBaseGitList
import com.example.githubparse.presentation.utils.mapper.toDomain
import com.example.githubparse.presentation.utils.mapper.toEntity2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryDataBaseGitListImpl @Inject constructor(
    private val gitListDao: GitListDao
) : RepositoryDataBaseGitList {
    override suspend fun executeDataBase(): Flow<List<GitHubItem>> {
        return gitListDao.getCurrentListRepo().map { users ->
            users.map { it.toDomain() }
        }
    }

    override suspend fun addUser(user: GitHubItem) {
        gitListDao.insertRepo(repo = user.toEntity2())
    }

}