package com.example.githubparse.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubparse.data.repository.Repository
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.models.getlist.GitHubList
import com.example.githubparse.domain.usecase.Dialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelActivity(): ViewModel() {
   // var repository = Repository(name)//переменная доступа к репозиторию
    var gitHubList: MutableLiveData<Response<GitHubList>> = MutableLiveData()//переменная списка
    var downloadList: MutableLiveData<List<GitUser>> = MutableLiveData()//переменная списка
    val repo by lazy { Repository() }
    fun getList(result: String){
        viewModelScope.launch {
            gitHubList.value = repo.getGitList(result)
        }
    }
    fun getDownloadList(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
           // downloadList.value = repo.executeDatabase(context)
            downloadList.postValue(repo.executeDatabase(context))
        }

    }





}