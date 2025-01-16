package com.example.githubparse.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    const val baseUrl = "https://api.github.com/"
    private fun getInstanse(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiService by lazy {
        getInstanse().create(ApiService::class.java)
    }
}