package com.example.githubparse.checkerror


sealed class ResponseResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): ResponseResult<T>(data)
    class Error<T>(data: T?, message: String?): ResponseResult<T>(data, message)
    class Loading<T>(): ResponseResult<T>()
}