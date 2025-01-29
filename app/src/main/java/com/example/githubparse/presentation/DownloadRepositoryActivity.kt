package com.example.githubparse.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.githubparse.databinding.ActivityDownloadRepositoryBinding
import com.example.githubparse.domain.adapter.adaptercalendar.CalendarAdapter
import com.example.githubparse.domain.adapter.adapterdownload.DownloadAdapter
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadRepositoryActivity : AppCompatActivity() {
    val adapter = DownloadAdapter()
    val adapterData = CalendarAdapter()
    private lateinit var binding: ActivityDownloadRepositoryBinding
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDb.adapter = adapter
        binding.rvDataDb.adapter = adapterData
        vm.getDownloadList(applicationContext)
        vm.downloadList.observe(this) { list ->
            list.let {
                adapter.setList(it)
            }
        }
        vm.getCalendarData()
        vm.calendarData.observe(this){ list ->
            binding.currentData.text = list.toString()
        }
        vm.downloadList.observe(this){ list ->
            list.let {
                adapterData.setList(it)
            }
        }
    }
}