package com.example.githubparse.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubparse.R
import com.example.githubparse.data.repository.Repository
import com.example.githubparse.domain.adapter.adapterdownload.DownloadAdapter
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_download_repository.*
import kotlinx.android.synthetic.main.activity_git_hub.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DownloadRepository : AppCompatActivity() {

    val adapter = DownloadAdapter(this)
    lateinit var vm: ViewModelActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_repository)
        vm = ViewModelProvider(this)[ViewModelActivity::class.java]
        rv_db.adapter = adapter
        vm.getDownloadList(applicationContext)
        vm.downloadList.observe(this) { list ->
            list.let {
                adapter.setList(it)
            }
        }
    }

}