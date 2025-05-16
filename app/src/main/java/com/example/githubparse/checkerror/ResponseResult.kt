package com.example.githubparse.checkerror

sealed class ResponseResult<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Error<T>(message: String?) : ResponseResult<T>(message = message)
    data object Loading : ResponseResult<Nothing>()
    class Null<T>: ResponseResult<T>()
}