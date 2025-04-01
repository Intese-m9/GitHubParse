package com.example.githubparse.domain.usecase

import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.models.getlist.GitHubListDTO
import retrofit2.Response
import javax.inject.Inject

class GetListGitUseCase @Inject constructor(private val apiRepositoryNet: RepositoryNet) {
  suspend fun getGitListUseCase(userName:String): Response<GitHubListDTO>{
       return apiRepositoryNet.getGitList(userName = userName)
    }
}