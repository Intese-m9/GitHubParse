package com.example.githubparse.domain.usecase

import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GitManager @Inject constructor(
    private val getListGitUseCase: GetListGitUseCase,
    private val getDataBaseGitUseCase: GetDataBaseGitUseCase,
    private val putDataBaseGitUser: PutDataBaseGitUser,
    private val deleteGitUserUseCase: DeleteGitUserUseCase,
) {
    suspend fun getListGit(name: String): Flow<ResponseResult<List<GitHubItem>>> {
        return getListGitUseCase.getGitListUseCase(userName = name)
    }

    suspend fun getDownloadListGit(): Flow<List<GitHubItem>> {
        return getDataBaseGitUseCase.getDownloadListGit()
    }

    suspend fun insertGitUser(user: GitHubItem) {
        return putDataBaseGitUser.insertGitUser(user = user)
    }

    suspend fun deleteUser(user: GitHubItem) {
        return deleteGitUserUseCase.deleteUser(user = user.fullName)
    }
}