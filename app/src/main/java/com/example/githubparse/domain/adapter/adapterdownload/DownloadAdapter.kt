package com.example.githubparse.domain.adapter.adapterdownload

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.R
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.databinding.ItemListBinding

class DownloadAdapter: RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {
    var downloadList = emptyList<GitUser>()
    class DownloadViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater)
        return DownloadViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        with(holder.binding){
            holder.binding.user.text = downloadList[position].repo
        }
    }
    override fun getItemCount(): Int {
        return downloadList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitUser>){
        downloadList = list
        notifyDataSetChanged()
    }
}