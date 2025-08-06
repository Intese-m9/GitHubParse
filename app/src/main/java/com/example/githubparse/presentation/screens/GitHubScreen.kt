package com.example.githubparse.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import com.example.githubparse.presentation.utils.checkerror.ResponseResult


@Composable
fun GitHubScreen(navController: NavController) {
    val viewModel: ViewModelActivity = hiltViewModel()
    GitHub(navController = navController, viewModel = viewModel)
}


@Composable
fun GitHub(
    navController: NavController,
    viewModel: ViewModelActivity,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = modifier.padding(10.dp)) {
        ToolbarComponent(viewModel = viewModel)
        GitNameLazyColumn(
            viewModel = viewModel,
            context = context
        ) { name, fullName ->
            Dialog(viewModelActivity = viewModel).openDialog(context = context, name, fullName)
        }

        Button(
            modifier = modifier,
            onClick = {
                navController.navigate("downloadGitActivity")
            }) {}
    }
}

@Composable
fun ToolbarComponent(viewModel: ViewModelActivity, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = modifier.padding(top = 30.dp) // Применяем отступ только к Row
    ) {
        TextField(
            modifier = Modifier.padding(end = 20.dp), // Отступ для TextField
            value = text,
            placeholder = { Text(text = "Введите пользователя git") },
            onValueChange = { newText -> text = newText }
        )

        Button(
            modifier = Modifier.fillMaxWidth(), // Если нужно, чтобы Button занимал всю ширину
            onClick = {
                val userName: String = text.text // Получаем текст из TextField
                viewModel.loadGitHubList(userName) // Передаем значение во ViewModel
            }
        ) {
            Text(text = "Поиск", modifier = Modifier.wrapContentSize())
        }
    }
}

@Composable
fun GitNameLazyColumn(
    viewModel: ViewModelActivity,
    context: Context,
    onItemClick: (String, String) -> Unit
) {
    val state by viewModel.gitHubList.collectAsState()

    when (val result = state) {
        is ResponseResult.Success -> {
            val repoList = result.data
            LazyColumn {
                items(repoList ?: emptyList()) { item ->
                    Text(
                        text = item.fullName,
                        modifier = Modifier
                            .clickable {
                                onItemClick(item.name, item.fullName)
                            }
                    )
                }
            }
        }

        is ResponseResult.Error -> {
            Toast.makeText(context, "Ошибка: $result", Toast.LENGTH_LONG).show()
        }

        is ResponseResult.Loading -> {
            CenteredLoadingIndicator()
            Toast.makeText(context, "Данные загружаются", Toast.LENGTH_LONG).show()
        }

        null -> {}
    }
}

@Composable
fun CenteredLoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Заполняем весь доступный размер
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center) // Центрируем индикатор
        )
    }
}