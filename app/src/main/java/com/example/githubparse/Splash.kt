package com.example.githubparse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubparse.presentation.GitHubActivity
import kotlinx.coroutines.*

class Splash : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        activityScope.launch {
            delay(3000)
            var intent = Intent(this@Splash, GitHubActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
