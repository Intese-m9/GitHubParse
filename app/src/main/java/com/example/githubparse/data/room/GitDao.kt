package com.example.githubparse.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GitDao {
    @Insert
    suspend fun insertRepo(repo: GitUser)
    @Query("SELECT * FROM repository_table")
    fun getAllRepos(): List<GitUser>
}