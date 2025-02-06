package com.example.githubparse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubActivity : AppCompatActivity() {
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubScreen(viewModel = vm)
        }
    }

    @Composable
    fun GitHubScreen(viewModel: ViewModelActivity, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(10.dp)) {
            ToolbarComponent()
            GitNameLazyColumn(viewModel = viewModel, modifier = modifier) { name, fullName ->
                Dialog().openDialog(context = this@GitHubActivity, name, fullName)
            }
        }
    }

    @Composable
    fun ToolbarComponent(modifier: Modifier = Modifier) {
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
                    vm.loadGitHubList(userName) // Передаем значение во ViewModel
                }
            ) {
                Text(text = "Поиск", modifier = Modifier.wrapContentSize())
            }
        }
    }

    @Composable
    fun GitNameLazyColumn(
        viewModel: ViewModelActivity,
        modifier: Modifier = Modifier,
        onItemClick: (String, String) -> Unit
    ) {
        val responseResult = viewModel.gitHubList.observeAsState()
        when (val result = responseResult.value) {
            is ResponseResult.Success -> {
                LazyColumn {
                    items(result.data ?: emptyList()) {
                        Text(
                            text = it.full_name,
                            modifier = modifier.clickable {
                                onItemClick(it.name, it.full_name)
                            }
                        )
                    }
                }
            }

            is ResponseResult.Error -> {
                Toast.makeText(this, "Ошибка: ${result.message}", Toast.LENGTH_LONG).show()
            }

            is ResponseResult.Loading -> {
                CenteredLoadingIndicator()
                Toast.makeText(this, "Данные загружаются", Toast.LENGTH_LONG).show()
            }

            null -> {
                Toast.makeText(this, "Данные отсутствуют", Toast.LENGTH_LONG).show()
            }

        }

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





