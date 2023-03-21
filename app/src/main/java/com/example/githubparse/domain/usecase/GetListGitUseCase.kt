package com.example.githubparse.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.repository.Repository
import com.example.githubparse.domain.models.getlist.GitHubList
import retrofit2.Response
import javax.inject.Inject

class GetListGitUseCase @Inject constructor(private val apiRepository: Repository) {
    suspend fun getGitHubList(text:String): Response<GitHubList>{
       return apiRepository.getGitList(text)
    }
}