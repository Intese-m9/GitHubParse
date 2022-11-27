package com.example.githubparse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubparse.R
import com.example.githubparse.domain.adapter.GitAdapter
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_git_hub.*

class GitHubActivity : AppCompatActivity() {
    val adapter = GitAdapter(this)
    lateinit var vm: ViewModelActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub)
        vm = ViewModelProvider(this)[ViewModelActivity::class.java]
        rv_git.adapter = adapter
        searchButton.setOnClickListener {
            var userName: String = search?.text.toString()
            vm.getList(userName)
            //val viewModel = ViewModelProvider(this, MyViewModelFactory(userName))[ViewModelActivity::class.java]
            vm.gitHubList.observe(this) { list ->
                list.body()?.let {
                    adapter.setList(it)
                }
            }
        }
        download.setOnClickListener {
            val intent = Intent(this, DownloadRepository::class.java)
            startActivity(intent)
        }


    }
}