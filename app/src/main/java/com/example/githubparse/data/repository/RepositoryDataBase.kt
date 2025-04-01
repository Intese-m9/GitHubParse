package com.example.githubparse.data.repository

import com.example.githubparse.data.room.GitUserDBO
import kotlinx.coroutines.flow.Flow

interface RepositoryDataBase {
   suspend fun executeDataBase(): Flow<List<GitUserDBO>>
   suspend fun addUser(user:GitUserDBO)
   suspend fun deleteUser(user: String)
}