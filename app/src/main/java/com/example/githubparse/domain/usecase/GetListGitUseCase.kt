package com.example.githubparse.domain.usecase

import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.repository.RepositoryNet
import com.example.githubparse.data.models.getlist.GitHubListDTO
import retrofit2.Response
import javax.inject.Inject

class GetListGitUseCase @Inject constructor(private val apiRepositoryNet: RepositoryNet) {
  suspend fun getGitListUseCase(userName:String): ResponseResult<GitHubListDTO>{
      apiRepositoryNet.getGitList(userName = userName).apply {
          return try {
              when (val response = this){
                  is ResponseResult.Loading->{
                      ResponseResult.Loading
                  }

                  is ResponseResult.Error -> {
                      ResponseResult.Error(response.message)
                  }
                  is ResponseResult.Null -> {
                      ResponseResult.Null()

                  }
                  is ResponseResult.Success -> {
                      return if(response.data != null){
                          println("DATA"+response.data)
                          ResponseResult.Success(response.data)
                      }else{
                          ResponseResult.Error(message = "data is null or empty")
                      }

                  }
              }
          }catch (e:Exception){
              ResponseResult.Error(message = e.message)
          }
      }
    }
}