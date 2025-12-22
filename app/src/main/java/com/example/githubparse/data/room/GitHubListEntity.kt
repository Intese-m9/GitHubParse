package com.example.githubparse.data.room

import androidx.room.Entity

@Entity(tableName = "github_list_table")
data class GitHubListEntity(
    val id:Int,
    val name:String,
    val fullName: String
)
