package com.example.githubparse.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubparse.presentation.viewmodel.ViewModelActivity

@Composable
fun TaskScreenExample() {
    val viewModel: ViewModelActivity = hiltViewModel()
    val state = viewModel.taskList.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(state.value) { item ->
                Text(
                    color = Color.Black,
                    text = item
                )
            }
        }
        Button(onClick = {
            viewModel.addNewTask("343")
            println(state.value)
        }) {
            Text("Добавить")
        }
    }


}