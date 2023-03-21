package com.example.githubparse.domain.usecase

import android.content.Context
import com.example.githubparse.data.repository.Repository
import com.example.githubparse.data.room.GitUser
import javax.inject.Inject

class GetDataBaseGitUseCase@Inject constructor(private val apiRepository: Repository) {
     fun getDownloadListGit(context: Context): List<GitUser>{
         return apiRepository.executeDatabase(context)
     }
}