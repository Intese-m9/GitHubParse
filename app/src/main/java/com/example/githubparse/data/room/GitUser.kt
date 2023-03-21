package com.example.githubparse.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository_table")
data class GitUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "repo")
    var repo: String
)