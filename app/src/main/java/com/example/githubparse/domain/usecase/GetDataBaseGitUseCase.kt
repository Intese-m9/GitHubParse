package com.example.githubparse.domain.usecase

import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitUserDBO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataBaseGitUseCase @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBase) {
    suspend fun getDownloadListGit(): Flow<List<GitUserDBO>> {
        return apiRepositoryDataBase.executeDataBase()
    }
}