package com.example.githubparse.presentation.utils.checkerror

sealed class ResponseResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Error<T>(message: String) : ResponseResult<T>(message = message)
    class Loading<T>() : ResponseResult<T>()
}


