package com.example.githubparse.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.api.RetrofitHelper
import com.example.githubparse.domain.models.getlist.GitHubList
import retrofit2.Response
import javax.inject.Inject

class RepositoryNet @Inject constructor() {
    private val _gitResponseLiveData = MutableLiveData<ResponseResult<GitHubList>>()
    val gitResponseLiveData: LiveData<ResponseResult<GitHubList>>
    get() = _gitResponseLiveData
    suspend fun getGitList(userName: String): Response<GitHubList>{
        _gitResponseLiveData.postValue(ResponseResult.Loading())
        val response = RetrofitHelper.api.getGitList(userName)
        when {
            response.isSuccessful && response.body() != null -> {
                _gitResponseLiveData.postValue(ResponseResult.Success(response.body()!!))
            }
            response.errorBody() != null -> {
                _gitResponseLiveData.postValue(ResponseResult.Error("Ошибка"))

            }
            else -> {
                _gitResponseLiveData.postValue(ResponseResult.Error("Ошибка"))
            }
        }
        return RetrofitHelper.api.getGitList(userName)
    }
}