package com.example.githubparse.domain.repository

import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow

interface RepositoryNet {
   suspend fun getGitList(userName: String): Flow<ResponseResult<List<GitHubItem>>>
}