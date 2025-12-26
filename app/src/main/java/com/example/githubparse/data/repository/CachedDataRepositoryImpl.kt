package com.example.githubparse.data.repository

import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.domain.repository.CachedDataRepository
import com.example.githubparse.domain.repository.RepositoryDataBaseGitList
import com.example.githubparse.domain.repository.RepositoryNet
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CachedDataRepositoryImpl @Inject constructor(
    private val repositoryNet: RepositoryNet,
    private val repositoryDataBaseGitList: RepositoryDataBaseGitList
) : CachedDataRepository {
    override suspend fun getData(): Flow<ResponseResult<List<GitHubItem>>> {
        return flow {
            repositoryDataBaseGitList.executeDataBase()
                .collect { cashedRepos ->
                    emit(ResponseResult.Success(cashedRepos))
                }
        }
    }
}