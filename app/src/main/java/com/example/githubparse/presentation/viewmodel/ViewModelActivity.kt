package com.example.githubparse.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.models.getlist.GitHubList
import com.example.githubparse.domain.usecase.GetCurrentDate
import com.example.githubparse.domain.usecase.GetDataBaseGitUseCase
import com.example.githubparse.domain.usecase.GetListGitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelActivity @Inject constructor(
    private val getListGitUseCase: GetListGitUseCase,
    private val getDataBaseGitUseCase: GetDataBaseGitUseCase,
    private val getCurrentDate: GetCurrentDate
) : ViewModel() {
    // Объявляем MutableLiveData
     private val _gitHubList: MutableLiveData<ResponseResult<GitHubList>> = MutableLiveData()//переменная списка
     private val _downloadList: MutableLiveData<List<GitUser>> = MutableLiveData()//переменная списка
     private val _calendarData: MutableLiveData<List<String>> = MutableLiveData()//переменная даты
    // Публичный доступ для просмотра
    val gitHubList: LiveData<ResponseResult<GitHubList>> get() = _gitHubList
    val downloadList: LiveData<List<GitUser>> get() = _downloadList
    val calendarData: LiveData<List<String>> get() = _calendarData

   fun loadGitHubList(result: String) {
        viewModelScope.launch {
            _gitHubList.postValue(ResponseResult.Loading())
            try{
                val response = getListGitUseCase.getGitHubList(result)
                if (response.isSuccessful && response.body() != null){
                    _gitHubList.postValue(ResponseResult.Success(response.body()!!))
                } else {
                    _gitHubList.postValue(ResponseResult.Error("Ошибка загрузки"))
                }
            }catch (e:Exception){
                _gitHubList.postValue(ResponseResult.Error("${e.message}"))
            }
        }
    }

  fun getDownloadList(context: Context) {
      viewModelScope.launch {
          _downloadList.postValue(getDataBaseGitUseCase.getDownloadListGit(context))
      }
  }

  fun getCalendarData() {
      viewModelScope.launch(Dispatchers.IO) {
          _calendarData.postValue(listOf(getCurrentDate.getCurrentDate()))
      }
  }
}