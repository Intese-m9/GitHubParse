package com.example.githubparse.domain.repository

import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow

interface CachedDataRepository {
    suspend fun getData():Flow<ResponseResult<List<GitHubItem>>>
}