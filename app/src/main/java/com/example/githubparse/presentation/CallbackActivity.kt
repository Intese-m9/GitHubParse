package com.example.githubparse.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubparse.databinding.ActivityCallbackBinding
import com.example.githubparse.domain.adapter.adapterCallBack.CallBackAdapter
import com.example.githubparse.domain.adapter.adapterCallBack.OnItemClickListener
import com.example.githubparse.data.models.callbackexample.CallBackListItemDTO

class CallbackActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var binding: ActivityCallbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemsList = mutableListOf(
            CallBackListItemDTO(1,"Test1"),
            CallBackListItemDTO(2,"Test2"),
            CallBackListItemDTO(3,"Test3")
        )
        val adapterCallBack = CallBackAdapter(itemsList,this)
        binding.callbackRv.layoutManager = LinearLayoutManager(this)
        binding.callbackRv.adapter = adapterCallBack
    }

    override fun onclick(item: CallBackListItemDTO) {
        Toast.makeText(this, item.name,Toast.LENGTH_SHORT).show()
    }


}

