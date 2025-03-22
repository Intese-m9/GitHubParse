package com.example.githubparse.domain.usecase

import com.example.githubparse.data.models.getlist.GitHubList
import com.example.githubparse.data.room.GitUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class GitManager @Inject constructor(
    private val getListGitUseCase: GetListGitUseCase,
    private val getDataBaseGitUseCase: GetDataBaseGitUseCase,
    private val putDataBaseGitUser: PutDataBaseGitUser,
    private val deleteGitUserUseCase: DeleteGitUserUseCase,
    ){
    suspend fun getListGit(name: String): Response<GitHubList> {
       return getListGitUseCase.getGitListUseCase(userName = name)
    }

    suspend fun getDownloadListGit(): Flow<List<GitUser>> {
        return getDataBaseGitUseCase.getDownloadListGit()
    }

    suspend fun insertGitUser(user:GitUser){
        return putDataBaseGitUser.insertGitUser(user = user)
    }

    suspend fun deleteUser(user:GitUser){
        return deleteGitUserUseCase.deleteUser(user = user.repo)
    }
}