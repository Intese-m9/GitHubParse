package com.example.githubparse.presentation.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubparse.data.repository.RepositoryDataBase
import com.example.githubparse.data.repositoryImpl.RepositoryDataBaseImpl
import com.example.githubparse.data.repositoryImpl.RepositoryNetImpl
import com.example.githubparse.domain.usecase.GetCurrentDate
import com.example.githubparse.domain.usecase.GetDataBaseGitUseCase
import com.example.githubparse.domain.usecase.GetListGitUseCase
import com.example.githubparse.domain.usecase.PutDataBaseGitUser
import com.example.githubparse.presentation.viewmodel.ViewModelActivity

@Composable
fun DownloadRepositoryScreen(modifier: Modifier = Modifier) {
    val viewModel: ViewModelActivity = hiltViewModel()
    viewModel.getDownloadListFromDB()
    viewModel.getCalendarData()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AllListDownloads(viewModel = viewModel)
        Spacer(Modifier.size(20.dp))
        ListInCurrentDate(viewModel = viewModel)
    }
}

@Composable
fun AllListDownloads(viewModel: ViewModelActivity) {
    val allListRepo = viewModel.downloadList.collectAsState()
    val context = LocalContext.current
    Column {
        Text(
            text = "Список загрузок",
            color = Color.Blue,
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Spacer(Modifier.size(20.dp))
        LazyColumn {
            items(allListRepo.value) { item ->
                Text(
                    modifier = Modifier.clickable {
                        viewModel.deleteUserInDataBase(item)
                    },
                    text = item.repo
                )
            }
        }
    }
}

@Composable
fun ListInCurrentDate(viewModel: ViewModelActivity) {
    val currentDate = viewModel.calendarData.collectAsState()
    val allListRepo = viewModel.downloadList.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            text = "Свежие загрузки на текущую дату: ${currentDate.value}",
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Spacer(Modifier.size(20.dp))
        LazyColumn {
            items(allListRepo.value) { item ->
                if (item.data == currentDate.value) {
                    Text(text = item.repo)
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewRepoScreen() {
    TODO("Разобрать с превьюшкой")
    //  DownloadRepositoryScreenPreview()
}

/*@Composable
private fun DownloadRepositoryScreenPreview(modifier: Modifier = Modifier) {
    val getCurrentDate = GetCurrentDate()
    val apiRepo = RepositoryDataBaseImpl()
    val putDataBaseGitUser = PutDataBaseGitUser()
    val repoGitDB = RepositoryDataBaseImpl()
    val getDataBaseGitUseCase = GetDataBaseGitUseCase(
        apiRepositoryDataBase = repoGitDB
    )
    val repoNet = RepositoryNetImpl()
    val getListGitUseCase = GetListGitUseCase(apiRepositoryNet = repoNet)
    val viewModel = ViewModelActivity(
        getCurrentDate = getCurrentDate,
        getDataBaseGitUseCase = getDataBaseGitUseCase,
        getListGitUseCase = getListGitUseCase,
        putDataBaseGitUser =

    )
    val context = LocalContext.current

    // Вызываем методы ViewModel
    if (!LocalInspectionMode.current) {
        viewModel.getDownloadListFromDB(context)
        viewModel.getCalendarData()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AllListDownloads(viewModel = viewModel)
        Spacer(Modifier.size(20.dp))
        ListInCurrentDate(viewModel = viewModel)
    }
}*/

