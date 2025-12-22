package com.example.githubparse.domain.adapter.adapterdownload

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.data.room.GitUserDownloadEntity
import com.example.githubparse.databinding.ItemListBinding

class DownloadAdapter: RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>(), View.OnClickListener {
    var downloadList = emptyList<GitUserDownloadEntity>()

    class DownloadViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(gitUser:GitUserDownloadEntity){
            binding.user.text = gitUser.repo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater)
        return DownloadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        holder.bind(downloadList[position])
    }

    override fun getItemCount(): Int {
        return downloadList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitUserDownloadEntity>){
        downloadList = list
        notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}