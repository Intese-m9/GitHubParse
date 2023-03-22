package com.example.githubparse.data.repository

import com.example.githubparse.data.api.RetrofitHelper
import com.example.githubparse.domain.models.getlist.GitHubList
import retrofit2.Response

class RepositoryNet {
    suspend fun getGitList(userName: String): Response<GitHubList>{
        return RetrofitHelper.api.getGitList(userName)
    }
}