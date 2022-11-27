package com.example.githubparse.data.repository

import android.content.Context
import androidx.room.Room
import com.example.githubparse.data.api.RetrofitHelper
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitDatabase
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.models.getlist.GitHubList
import retrofit2.Response

class Repository() {
    private lateinit var gitDao: GitDao
    suspend fun getGitList(userName: String): Response<GitHubList>{
        return RetrofitHelper.api.getGitList(userName)
    }
    fun executeDatabase(context: Context): List<GitUser> {
        val db = Room.databaseBuilder(
            context,
            GitDatabase::class.java, "repository_database"
        ).build()//создаем базу данных
        gitDao = db.GitDao()//получили экземпляр бд
        return gitDao.getAllRepos()
    }

}