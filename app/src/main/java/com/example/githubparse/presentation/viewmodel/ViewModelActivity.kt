package com.example.githubparse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.data.room.GitUserDBO
import com.example.githubparse.data.models.getlist.GitHubListDTO
import com.example.githubparse.domain.usecase.GetCurrentDate
import com.example.githubparse.domain.usecase.GitManager
import com.example.githubparse.domain.usecase.TaskExampleUseCase
import com.example.githubparse.presentation.utils.BoundServiceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelActivity @Inject constructor(
    private val getCurrentDate: GetCurrentDate,
    private val taskExampleUseCase: TaskExampleUseCase,
    private val boundService: BoundServiceUtils,
    private val gitManager: GitManager
) : ViewModel() {
    // Объявляем MutableLiveData
    private val _gitHubList: MutableStateFlow<ResponseResult<GitHubListDTO>> =
        MutableStateFlow(ResponseResult.Null())
    private val _downloadList: MutableStateFlow<List<GitUserDBO>> =
        MutableStateFlow(emptyList())//переменная списка
    private val _calendarData: MutableStateFlow<String> = MutableStateFlow("")//переменная даты
    private val _itemIdInt: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _taskList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    // Публичный доступ для просмотра
    val gitHubList: StateFlow<ResponseResult<GitHubListDTO>> = _gitHubList
    val downloadList: StateFlow<List<GitUserDBO>> get() = _downloadList
    val calendarData: StateFlow<String> get() = _calendarData
    val itemInt: StateFlow<Int> get() = _itemIdInt
    val taskList: StateFlow<List<String>> = _taskList

    init {
        getDownloadListFromDB()
    }

    fun loadGitHubList(userName: String) {
        viewModelScope.launch {
            val response = gitManager.getListGit(name = userName)
            try {
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

    fun getDownloadListFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            println("Запуск в потоке: ${Thread.currentThread().name}")
            gitManager.getDownloadListGit().collect { userList ->
                _downloadList.value = userList
            }
        }
    }

    fun insertGitUserInDataBase(user: GitUserDBO) {
        viewModelScope.launch(Dispatchers.IO) {
            gitManager.insertGitUser(user = user)
        }
    }

    fun getCalendarData() {
        viewModelScope.launch(Dispatchers.Default) {
            _calendarData.value = getCurrentDate.getCurrentDate()
        }
    }

    fun deleteUserInDataBase(user: GitUserDBO) {
        viewModelScope.launch(Dispatchers.IO) {
            gitManager.deleteUser(user)
        }
    }

    fun addNewTask(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskExampleUseCase.addItemInList(item = name)
            _taskList.value = taskExampleUseCase.getList()
        }
    }

    fun bindService() {
        viewModelScope.launch {
            boundService.bindService()
        }
    }

    fun unbindService() {
        viewModelScope.launch {
            boundService.unbindService()
        }
    }

    fun performActionFromBoundService() {
        viewModelScope.launch(Dispatchers.IO) {
            boundService.performAction { item ->
                _itemIdInt.value = item
            }
        }
    }
}