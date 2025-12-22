package com.example.githubparse.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GitDownloadDao {
    @Insert(entity = GitUserDownloadEntity::class)
    suspend fun insertRepo(repo: GitUserDownloadEntity)

    @Query("SELECT * FROM repository_table")
    fun getAllRepos(): Flow<List<GitUserDownloadEntity>>

    @Query("DELETE FROM repository_table WHERE repo = :repo")
    fun deleteCurrentUser(repo: String)
}

@Dao
interface GitListDao {
    @Insert
    suspend fun insertRepo(repo: GitHubListEntity)

    @Query("SELECT * FROM github_list_table")
    suspend fun getCurrentListRepo(): Flow<List<GitHubListEntity>>
}