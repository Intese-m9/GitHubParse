package com.example.githubparse.data.repositoryImpl

import android.content.Context
import androidx.room.Room
import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitDatabase
import com.example.githubparse.data.room.GitUser
import javax.inject.Inject

class RepositoryDataBaseImpl @Inject constructor(): RepositoryDataBase {
    private lateinit var gitDao: GitDao
    override fun executeDataBase(context: Context): List<GitUser> {
        val db = Room.databaseBuilder(context, GitDatabase::class.java, "repository_database")
            .build()//создаем базу данных
        gitDao = db.GitDao()//получили экземпляр бд
        return gitDao.getAllRepos()
    }
}

