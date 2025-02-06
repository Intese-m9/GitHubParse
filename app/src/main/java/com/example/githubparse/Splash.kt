package com.example.githubparse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubparse.presentation.GitHubActivity
import kotlinx.coroutines.*

class Splash : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(modifier = Modifier.align(Alignment.Center), text = "Splash")
            }
        }
        activityScope.launch {
            delay(3000)
            val intent = Intent(this@Splash, GitHubActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
