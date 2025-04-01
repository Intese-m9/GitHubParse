package com.example.githubparse.domain.usecase

import com.example.githubparse.data.models.getlist.GitHubListDTO
import com.example.githubparse.data.room.GitUserDBO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class GitManager @Inject constructor(
    private val getListGitUseCase: GetListGitUseCase,
    private val getDataBaseGitUseCase: GetDataBaseGitUseCase,
    private val putDataBaseGitUser: PutDataBaseGitUser,
    private val deleteGitUserUseCase: DeleteGitUserUseCase,
    ){
    suspend fun getListGit(name: String): Response<GitHubListDTO> {
       return getListGitUseCase.getGitListUseCase(userName = name)
    }

    suspend fun getDownloadListGit(): Flow<List<GitUserDBO>> {
        return getDataBaseGitUseCase.getDownloadListGit()
    }

    suspend fun insertGitUser(user:GitUserDBO){
        return putDataBaseGitUser.insertGitUser(user = user)
    }

    suspend fun deleteUser(user:GitUserDBO){
        return deleteGitUserUseCase.deleteUser(user = user.repo)
    }
}