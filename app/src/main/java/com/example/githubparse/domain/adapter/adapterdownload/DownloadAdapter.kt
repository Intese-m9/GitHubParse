package com.example.githubparse.domain.adapter.adapterdownload

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.R
import com.example.githubparse.data.room.GitUser
import kotlinx.android.synthetic.main.item_list.view.*

class DownloadAdapter: RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {
    var downloadList = emptyList<GitUser>()
    class DownloadViewHolder(view: View): RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return DownloadViewHolder(view)
    }
    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        holder.itemView.user.text = downloadList[position].repo
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