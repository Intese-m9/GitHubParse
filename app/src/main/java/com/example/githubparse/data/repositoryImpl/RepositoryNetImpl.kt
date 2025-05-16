package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.api.RetrofitHelper
import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.models.getlist.GitHubListDTO
import com.example.githubparse.presentation.utils.BaseApiResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryNetImpl @Inject constructor() : BaseApiResponse(),RepositoryNet {
    override suspend fun getGitList(userName: String): ResponseResult<GitHubListDTO> {
        return safeApiCall {
            RetrofitHelper.api.getGitList(userName = userName)
        }
    }
}
//RetrofitHelper.api.getGitList(userName = userName)