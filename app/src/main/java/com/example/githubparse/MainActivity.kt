package com.example.githubparse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubparse.presentation.screens.DownloadRepositoryScreen
import com.example.githubparse.presentation.screens.GitHubScreen
import com.example.githubparse.presentation.screens.SplashScreen
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: ViewModelActivity by viewModels()
        enableEdgeToEdge()
        setContent {
            GitApp(viewModelActivity = vm)
        }
    }
}

@Composable
fun GitApp(viewModelActivity: ViewModelActivity) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("gitHubActivity") {
            GitHubScreen(navController = navController, viewModel = viewModelActivity)
        }
        composable("downloadGitActivity") {
            DownloadRepositoryScreen(viewModel = viewModelActivity)
        }
    }
}

