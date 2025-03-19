package com.example.githubparse.presentation.screens

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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubparse.presentation.viewmodel.ViewModelActivity

@Composable
fun DownloadRepositoryScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: ViewModelActivity = hiltViewModel()
    viewModel.getDownloadListFromDB()
    viewModel.getCalendarData()
    viewModel.performActionFromBoundService()

    DisposableEffect(key1 = Unit) {
        viewModel.bindService()
        onDispose {
            viewModel.unbindService()
        }
    }
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AllListDownloads(viewModel = viewModel)
        Spacer(Modifier.size(20.dp))
        ListInCurrentDate(viewModel = viewModel)
        Spacer(modifier = Modifier.size(30.dp))
        StateProgressInBoundService(viewModel = viewModel)
    }
}

@Composable
fun AllListDownloads(viewModel: ViewModelActivity) {
    val allListRepo = viewModel.downloadList.collectAsState()
    Column {
        Text(
            text = "Список загрузок", color = Color.Blue, style = TextStyle(
                fontSize = 20.sp
            )
        )
        Spacer(Modifier.size(20.dp))
        LazyColumn {
            items(allListRepo.value) { item ->
                Text(
                    modifier = Modifier.clickable {
                        viewModel.deleteUserInDataBase(item)
                    }, text = item.repo
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

@Composable
fun StateProgressInBoundService(viewModel: ViewModelActivity){
    val sateId = viewModel.itemInt.collectAsState()
    Text(text = "${sateId.value}")
}
