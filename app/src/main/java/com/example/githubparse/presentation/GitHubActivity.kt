package com.example.githubparse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubparse.R
import com.example.githubparse.domain.adapter.adaptergit.GitAdapter
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_git_hub.*
@AndroidEntryPoint
class GitHubActivity : AppCompatActivity() {
    val adapter = GitAdapter(this, dialog = Dialog())
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub)
        rv_git.adapter = adapter
        searchButton.setOnClickListener {
            val userName: String = search?.text.toString()//парсим значение из toolbar
            vm.getList(userName)//передаем во viewmodel значение во вкладке поиск (наименование)
            vm.gitHubList.observe(this) { list ->
                list.body()?.let {
                    adapter.setList(it)
                }
            }
        }
        download.setOnClickListener {
            val intent = Intent(this, DownloadRepositoryActivity::class.java)
            startActivity(intent)
        }
    }
}