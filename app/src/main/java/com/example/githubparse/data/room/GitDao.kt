package com.example.githubparse.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GitDao {
    @Insert
    suspend fun insertRepo(repo: GitUserEntity)
    @Query("SELECT * FROM repository_table")
    fun getAllRepos(): Flow<List<GitUserEntity>>
    @Query("DELETE FROM repository_table WHERE repo = :repo")
    fun deleteCurrentUser(repo:String)
}