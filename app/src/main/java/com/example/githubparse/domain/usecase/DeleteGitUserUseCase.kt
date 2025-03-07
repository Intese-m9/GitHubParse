package com.example.githubparse.domain.usecase

import com.example.githubparse.data.repository.RepositoryDataBase
import javax.inject.Inject

class DeleteGitUserUseCase @Inject constructor(private val apiRepositoryDataBase: RepositoryDataBase){
    suspend fun deleteUser(user: String){
        apiRepositoryDataBase.deleteUser(user = user)
    }
}