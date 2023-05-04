package com.example.githubparse.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.githubparse.R
import com.example.githubparse.domain.adapter.adaptercalendar.CalendarAdapter
import com.example.githubparse.domain.adapter.adapterdownload.DownloadAdapter
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_download_repository.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DownloadRepositoryActivity : AppCompatActivity() {
    val adapter = DownloadAdapter()
    val adapterData = CalendarAdapter()
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_repository)
        rv_db.adapter = adapter
        vm.getDownloadList(applicationContext)
        vm.downloadList.observe(this) { list ->
            list.let {
                adapter.setList(it)
                adapterData.setList(it)
            }
        }
        vm.getData()
        vm.dataList.observe(this){ list ->
          currentData.text = list.toString()
        }

    }
}