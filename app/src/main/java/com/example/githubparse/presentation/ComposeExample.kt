package com.example.githubparse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubparse.R

class ComposeExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                LazyColumn {
                    items(20){
                        NewsStory()
                    }
                }
            }
        }
    }
}


@Composable
fun NewsStory() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp),
                painter = painterResource(id = R.drawable.ic_baseline_help_outline_24),
                contentDescription = "Test_Image"
            )
            Text(
                text = "ivan.balandin@rosbank.ru",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "8927722333",
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 30.dp),
                text = "Samara"
            )
        }
    }
}

@Composable
fun ElevatedCard() {
    Column {
        // create state for buttonCount and a function to update it -
        // setButtonCount
        val (buttonCount, setButtonCount) =
            rememberSaveable { mutableStateOf(0) }
        Button(onClick = {
            setButtonCount(buttonCount + 1)
        }) {
            Text(text = "Press Me!")
        }
        // recomposes whenever button is pressed
        Text(text = "Button Pressed $buttonCount")
    }
}

@Composable
fun ElevatedCardTest() {
    val (current_value,update_value) = rememberSaveable { mutableIntStateOf(0) }
    Column {
        Button(onClick = {
            update_value(current_value+1)
        }) {
            Text(
                text = "Нажми меня"
            )
        }
        Text(text = "$current_value")
    }
}

@Preview
@Composable
fun PreviewNewStory() {
    NewsStory()
}




