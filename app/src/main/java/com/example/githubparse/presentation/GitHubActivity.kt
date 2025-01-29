package com.example.githubparse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.githubparse.databinding.ActivityGitHubBinding
import com.example.githubparse.domain.adapter.adaptergit.GitAdapter
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGitHubBinding
    val adapter = GitAdapter(onItemClick = { item ->
        Dialog().openDialog(this@GitHubActivity, item.name, item.full_name)
    })
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.gitHubList.observe(this) { list ->
            list.data?.let { items ->
                adapter.setList(items)
            }
        }
        binding.rvGit.adapter = adapter
        binding.searchButton.setOnClickListener {
            val userName: String = binding.search.text.toString()//парсим значение из toolbar
            vm.loadGitHubList(userName)//передаем во viewmodel значение во вкладке поиск (наименование)
        }
        binding.download.setOnClickListener {
            val intent = Intent(this, DownloadRepositoryActivity::class.java)
            startActivity(intent)
        }
        binding.download.setOnClickListener {
            val intent = Intent(this, ComposeExample::class.java)
            startActivity(intent)
        }
    }


}