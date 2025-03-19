package com.example.githubparse.data.repository

import com.example.githubparse.data.room.GitUser
import kotlinx.coroutines.flow.Flow

interface RepositoryDataBase {
   suspend fun executeDataBase(): Flow<List<GitUser>>
   suspend fun addUser(user:GitUser)
   suspend fun deleteUser(user: String)
}