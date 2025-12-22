package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.repository.RepositoryDataBaseDownloadList
import javax.inject.Inject

class DeleteGitUserUseCase @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBaseDownloadList){
    suspend fun deleteUser(user: String){
        apiRepositoryDataBase.deleteUser(user = user)
    }
}