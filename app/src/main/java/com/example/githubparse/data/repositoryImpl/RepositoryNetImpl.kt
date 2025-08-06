package com.example.githubparse.data.repositoryImpl

import com.example.githubparse.data.api.RetrofitHelper.api
import com.example.githubparse.domain.repository.RepositoryNet
import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.utils.mapper.toDomain
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryNetImpl @Inject constructor(
) : RepositoryNet {
    override suspend fun getGitList(userName: String): Flow<ResponseResult<List<GitHubItem>>> =
        flow {
            emit(ResponseResult.Loading())
            try {
                val getUsers = api.getGitList(userName = userName).map { users ->
                    users.toDomain()
                }
                emit(ResponseResult.Success(getUsers))
            } catch (e: retrofit2.HttpException) {
                emit(ResponseResult.Error(e.message.toString()))
            } catch (e: Exception) {
                emit(ResponseResult.Error(e.message.toString()))
            }
        }
}
