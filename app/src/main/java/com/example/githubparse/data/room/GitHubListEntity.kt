package com.example.githubparse.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_list_table")
data class GitHubListEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val fullName: String
)
