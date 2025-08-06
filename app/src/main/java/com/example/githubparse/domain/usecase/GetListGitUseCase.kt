package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.domain.repository.RepositoryNet
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListGitUseCase @Inject constructor(private val apiRepositoryNet: RepositoryNet) {
    suspend fun getGitListUseCase(userName:String): Flow<ResponseResult<List<GitHubItem>>>{
        return apiRepositoryNet.getGitList(userName = userName)
    }
}