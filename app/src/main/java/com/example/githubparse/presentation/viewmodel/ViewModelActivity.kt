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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelActivity @Inject constructor(
    private val getListGitUseCase: GetListGitUseCase,
    private val getDataBaseGitUseCase: GetDataBaseGitUseCase,
    private val getCurrentDate: GetCurrentDate
) : ViewModel() {
    // Объявляем MutableLiveData
    private val _gitHubList: MutableStateFlow<ResponseResult<GitHubList>> =
        MutableStateFlow(ResponseResult.Null())//переменная списка
    private val _downloadList: MutableStateFlow<List<GitUser>> =
        MutableStateFlow(emptyList())//переменная списка
    private val _calendarData: MutableStateFlow<String> = MutableStateFlow("")//переменная даты

    // Публичный доступ для просмотра
    val gitHubList: StateFlow<ResponseResult<GitHubList>> get() = _gitHubList
    val downloadList: StateFlow<List<GitUser>> get() = _downloadList
    val calendarData: StateFlow<String> get() = _calendarData

    fun loadGitHubList(result: String) {
        viewModelScope.launch {
            _gitHubList.value = ResponseResult.Loading()
            try {
                val response = getListGitUseCase.getGitHubList(result)
                if (response.isSuccessful && response.body() != null) {
                    _gitHubList.value = ResponseResult.Success(data = response.body()!!)
                } else {
                    _gitHubList.value = ResponseResult.Error(message = response.message())
                }
            } catch (e: Exception) {
                _gitHubList.value = ResponseResult.Error(message = "${e.message}")
            }
        }
    }

    fun getDownloadList(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _downloadList.value = getDataBaseGitUseCase.getDownloadListGit(context = context)
        }
    }

    fun getCalendarData() {
        viewModelScope.launch(Dispatchers.Default) {
            _calendarData.value = getCurrentDate.getCurrentDate()
        }
    }
}