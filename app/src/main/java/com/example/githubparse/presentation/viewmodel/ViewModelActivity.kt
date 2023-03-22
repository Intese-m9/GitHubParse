package com.example.githubparse.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.models.getlist.GitHubList
import com.example.githubparse.domain.usecase.GetDataBaseGitUseCase
import com.example.githubparse.domain.usecase.GetListGitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelActivity @Inject constructor(private val getListGitUseCase: GetListGitUseCase,
                                            private val getDataBaseGitUseCase: GetDataBaseGitUseCase
) : ViewModel() {
    var gitHubList: MutableLiveData<Response<GitHubList>> = MutableLiveData()//переменная списка
    var downloadList: MutableLiveData<List<GitUser>> = MutableLiveData()//переменная списка
    fun getList(result: String) {
        viewModelScope.launch {
            gitHubList.value = getListGitUseCase.getGitHubList(result)
        }
    }
    fun getDownloadList(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            // downloadList.value = repo.executeDatabase(context)
           // downloadList.postValue(repo.executeDatabase(context))
            downloadList.postValue(getDataBaseGitUseCase.getDownloadListGit(context))
        }
    }
}