package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryDataBaseImpl @Inject constructor(private val gitDao: GitDao) : RepositoryDataBase {
    override suspend fun executeDataBase(): Flow<List<GitUser>> {
        return gitDao.getAllRepos()
    }

    override suspend fun addUser(user: GitUser) {
        gitDao.insertRepo(repo = user)
    }

    override suspend fun deleteUser(user: String) {
        gitDao.deleteCurrentUser(repo = user)
    }
}

