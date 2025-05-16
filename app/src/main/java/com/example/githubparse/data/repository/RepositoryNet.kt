package com.example.githubparse.data.repository

import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.models.getlist.GitHubListDTO
import retrofit2.Response

interface RepositoryNet{
   suspend fun getGitList(userName:String): ResponseResult<GitHubListDTO>
}