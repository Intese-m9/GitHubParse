package com.example.githubparse.data.api

import com.example.githubparse.domain.models.getlist.GitHubList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{username}/repos?page=1")
    suspend fun getGitList(
        @Path("username") userName: String
    ): Response<GitHubList>
}