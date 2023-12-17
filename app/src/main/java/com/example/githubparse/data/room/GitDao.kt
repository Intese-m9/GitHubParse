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
    // TODO: добавить сюда реализацию вывода по текущей дате. В селект добавить условие выборки за текущий день
}