package com.example.githubparse.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubparse.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ComposeExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TaskScreen()
            }
        }
    }
}

data class Task(
    val name:String,
    val isComplete:Boolean
)

@Composable
fun TaskList(taskList:MutableList<Task>,onItemClickListener:(Task)-> Unit){
    LazyColumn {
        items(taskList){items ->
            Text(text = items.name, modifier = Modifier.clickable { onItemClickListener(items) })
        }
    }
}
@Composable
fun InputTextAndButton(newItem:(Task)-> Unit){
    var newText by remember { mutableStateOf("") }
    TextField(value = newText, onValueChange = {newText = it})
    val newTask = Task(name = newText, isComplete = false)
    Button(onClick = {newItem(newTask)}) {
        Text(text = "Добавить заметку")
    }
}

@Composable
fun TaskScreen(){
    val listTask = remember {
        mutableStateListOf<Task>()
    }
    Column {
        InputTextAndButton {item ->
            listTask.add(item)
        }
        TaskList(taskList = listTask) {click ->
            println(click)
        }
    }
}



