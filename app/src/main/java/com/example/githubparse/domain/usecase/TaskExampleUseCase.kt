package com.example.githubparse.domain.usecase

import javax.inject.Inject

class TaskExampleUseCase @Inject constructor() {
    private val list = mutableListOf<String>()
    fun addItemInList(item:String){
        list.add(item)
    }
    fun getList(): List<String> {
        return list.toList()
    }
}