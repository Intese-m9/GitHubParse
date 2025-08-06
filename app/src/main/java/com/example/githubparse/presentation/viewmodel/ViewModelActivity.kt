package com.example.githubparse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.domain.usecase.GetCurrentDate
import com.example.githubparse.domain.usecase.GitManager
import com.example.githubparse.domain.usecase.TaskExampleUseCase
import com.example.githubparse.presentation.utils.checkerror.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelActivity @Inject constructor(
    private val getCurrentDate: GetCurrentDate,
    private val taskExampleUseCase: TaskExampleUseCase,
    private val gitManager: GitManager
) : ViewModel() {
    // Объявляем MutableLiveData
    private val _gitHubList: MutableStateFlow<ResponseResult<List<GitHubItem>>?> = MutableStateFlow(
        null)
    private val _downloadList: MutableStateFlow<List<GitHubItem>> =
        MutableStateFlow(emptyList())//переменная списка
    private val _calendarData: MutableStateFlow<String> = MutableStateFlow("")//переменная даты
    private val _itemIdInt: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _taskList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    // Публичный доступ для просмотра
    val gitHubList: StateFlow<ResponseResult<List<GitHubItem>>?> get() = _gitHubList
    val downloadList: StateFlow<List<GitHubItem>> get() = _downloadList
    val calendarData: StateFlow<String> get() = _calendarData
    val itemInt: StateFlow<Int> get() = _itemIdInt
    val taskList: StateFlow<List<String>> = _taskList

    init {
        getDownloadListFromDB()
    }

    fun loadGitHubList(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            gitManager.getListGit(name = userName)
                .collect { result ->
                    _gitHubList.value = result
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

    fun insertGitUserInDataBase(user: GitHubItem) {
        viewModelScope.launch(Dispatchers.IO) {
            gitManager.insertGitUser(user = user)
        }
    }

    fun getCalendarData() {
        viewModelScope.launch(Dispatchers.Default) {
            _calendarData.value = getCurrentDate.getCurrentDate()
        }
    }

    fun deleteUserInDataBase(user: GitHubItem) {
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
}