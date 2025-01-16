package com.example.githubparse.data.repository

import android.content.Context
import com.example.githubparse.data.room.GitUser

interface RepositoryDataBase {
    fun executeDataBase(context:Context):List<GitUser>
}