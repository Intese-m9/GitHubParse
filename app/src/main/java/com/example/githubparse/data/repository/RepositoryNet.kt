package com.example.githubparse.data.repository

import com.example.githubparse.data.models.getlist.GitHubList
import retrofit2.Response

interface RepositoryNet{
   suspend fun getGitList(userName:String): Response<GitHubList>
}