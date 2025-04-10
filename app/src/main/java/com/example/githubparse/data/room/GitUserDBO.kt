package com.example.githubparse.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository_table")
data class GitUserDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repository_id")
    var id: Int,
    @ColumnInfo(name = "repo")
    var repo: String,
    @ColumnInfo(name = "data")
    var data: String
)