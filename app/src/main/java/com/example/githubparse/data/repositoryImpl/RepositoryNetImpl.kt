package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.data.api.RetrofitHelper
import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.models.getlist.GitHubList
import retrofit2.Response
import javax.inject.Inject

class RepositoryNetImpl @Inject constructor() : RepositoryNet {
    override suspend fun getGitList(userName: String): Response<GitHubList> {
        return RetrofitHelper.api.getGitList(userName = userName)
    }

}
