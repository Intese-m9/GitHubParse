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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
                JuniorTestCodeToRefactor()
            }
        }
    }
}

@Composable
fun ExampleError(){
    Box {
        var imageHeightPx by remember { mutableIntStateOf(0) }

        Image(
            painter = painterResource(R.drawable.ic_baseline_help_outline_24),
            contentDescription = "I'm above the text",
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    // Не делайте так
                    imageHeightPx = size.height
                }
        )

        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) { imageHeightPx.toDp() }
            )
        )
    }
}

@Composable
fun Counter(){
    var clickState by remember{ mutableIntStateOf(0) }
    Column {
        Spacer(modifier = Modifier.size(60.dp))
        Text(text = "Clicks: $clickState", fontSize = 28.sp)
        Text(
            text = "+",
            fontSize = 36.sp,
            modifier = Modifier
                .clickable {
                    clickState++
                }
                .border(1.dp, Color.DarkGray)
                .padding(10.dp, 0.dp)
        )
    }
}


@Composable
fun JuniorTestCodeToRefactor() {
    val userName = remember { mutableStateOf("John Doe") }
    val age = remember { mutableStateOf("") }
    val isAdult = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Name: $userName", fontSize = 24.sp)

        TextField(
            value = age.value,
            textStyle = TextStyle(fontSize = 24.sp),
            onValueChange = { newText ->
                age.value = newText
                isAdult.value = if (newText.isNotEmpty()){
                    try {
                        newText.toInt() >=18
                    }catch (e:NumberFormatException){
                        false// Устанавливаем false, если не удалось преобразовать
                    }
                }else{// Если строка пустая, то не взрослый
                    false
                }
            })
        Text("Is Adult: ${isAdult.value}", fontSize = 24.sp)

        Button(onClick = {
          val currentAge = age.value.toIntOrNull() ?:0
            age.value = (currentAge + 1).toString()
            isAdult.value = currentAge +1 >=18
        }) {
            Text("Increment Age")
        }

        Button(onClick = {
            userName.value = "Jane Doe"
        }) {
            Text("Change Name")
        }
    }
}


@Composable
fun testModifier() {
    Column {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .padding(100.dp)
                .size(100.dp)
        )
    }
}


@Composable
fun testSideEffect() {
    val count = remember { mutableIntStateOf(0) }

    SideEffect {
        Log.d("SIDE_EFFECT", "Count: ${count.intValue}")
    }

    Column {
        Spacer(modifier = Modifier.size(50.dp))
        Button(onClick = { count.intValue++ }) {
            Text(text = "Click me")
        }
        Text("Count: ${count.intValue}")
    }

}

@Composable
fun testErrorSideEffect() {
    val count = remember { mutableIntStateOf(0) }
    SideEffect {
        Log.d("SIDE_ERROR_CHECK", "Count: ${count.intValue}")
    }
    Column {
        Spacer(modifier = Modifier.size(50.dp))
        Button(onClick = { count.intValue++ }) {
            SideEffect {
                Log.d("SIDE_ERROR_CHECK_INNER", "Count: ${count.intValue}")
            }
            Text(text = "Click count:${count.intValue}")
        }
    }
}

@Composable
fun testErrorLaunchedEffect() {
    val isLoading = remember { mutableStateOf(false) }
    val dataFetch = remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(isLoading.value) {
        val data = fetchData()
        dataFetch.value = data
        isLoading.value = false
    }

    Column {
        Spacer(modifier = Modifier.size(100.dp))
        Button(onClick = { isLoading.value = true }) {
            Text("Получить данные")
        }
        if (isLoading.value) {
            CircularProgressIndicator()
        }
        LazyColumn {
            items(dataFetch.value.size) { item ->
                Text(text = dataFetch.value[item])
            }
        }
    }
}

private suspend fun fetchData(): List<String> {
    delay(3000)
    return listOf("Data 1", "Data 2", "Data 3")
}

@Composable
fun testDispossableEffect() {
    val isVisibile = remember { mutableStateOf(false) }
    val isCounter = remember { mutableIntStateOf(0) }

    DisposableEffect(isVisibile.value) {
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope.launch {
            while (isVisibile.value) {
                delay(1000)
                isCounter.intValue++
            }
        }

        onDispose {
            coroutineScope.cancel()
        }
    }
    Column {
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Timer: ${isCounter.intValue}")
        Button(onClick = {
            isVisibile.value = !isVisibile.value
        }) {
            Text(text = "старт/стоп")
        }
    }
}
