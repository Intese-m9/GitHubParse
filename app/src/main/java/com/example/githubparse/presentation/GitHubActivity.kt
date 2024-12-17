package com.example.githubparse.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.githubparse.R
import com.example.githubparse.checkerror.ResponseResult
import com.example.githubparse.databinding.ActivityGitHubBinding
import com.example.githubparse.domain.adapter.adaptergit.GitAdapter
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.presentation.viewmodel.ViewModelActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGitHubBinding

   /* val adapter = GitAdapter(onItemClick = { item->
        this@GitHubActivity.openDialog(this@GitHubActivity.applicationContext, item.name, item.full_name)
    })*/
    val adapter = GitAdapter(onItemClick = {item->
        Dialog().openDialog(this@GitHubActivity,item.name,item.full_name)
   })
    private val vm: ViewModelActivity by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitHubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.gitHubList.observe(this) { list ->
            list.body()?.let {
                adapter.setList(it)
            }
        }
        val animateDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector)
        binding.imageViewAnimation.setImageDrawable(animateDrawable)
        vm.userResponseLiveData.observe(this) {
            when (it) {
                is ResponseResult.Success -> {
                    Toast.makeText(applicationContext, "Данные загружены", Toast.LENGTH_SHORT)
                        .show()
                }

                is ResponseResult.Error -> {
                    Toast.makeText(applicationContext, "Возникла ошибка!", Toast.LENGTH_SHORT)
                        .show()

                }

                is ResponseResult.Loading -> {

                }

            }
        }
        binding.rvGit.adapter = adapter
        binding.searchButton.setOnClickListener {
            val userName: String = binding.search.text.toString()//парсим значение из toolbar
            vm.getList(userName)//передаем во viewmodel значение во вкладке поиск (наименование)
        }
        binding.download.setOnClickListener {
            val intent = Intent(this, DownloadRepositoryActivity::class.java)
            startActivity(intent)
        }
        binding.download.setOnClickListener {
            val intent = Intent(this,CallbackActivity::class.java)
            startActivity(intent)
        }
    }


}