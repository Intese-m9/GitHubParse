package com.example.githubparse.domain.usecase

import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitUserDBO
import javax.inject.Inject


class PutDataBaseGitUser @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBase) {
    suspend fun insertGitUser(user: GitUserDBO) {
        apiRepositoryDataBase.addUser(user)
    }
}