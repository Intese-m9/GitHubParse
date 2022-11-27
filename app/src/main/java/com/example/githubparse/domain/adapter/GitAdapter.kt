package com.example.githubparse.domain.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.githubparse.R
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitDatabase
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.models.getlist.GitHubListItem
import com.example.githubparse.domain.usecase.Dialog
import com.example.githubparse.domain.usecase.DownloadGitStorage
import com.example.githubparse.domain.usecase.OpenGitBrowser
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.item_list.view.*

class GitAdapter(private val context: Context): RecyclerView.Adapter<GitAdapter.GitViewHolder>() {
    val dialog by lazy { Dialog() }
    var listGit = emptyList<GitHubListItem>()
    class GitViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitAdapter.GitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return GitViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitAdapter.GitViewHolder, position: Int) {
        holder.itemView.user.text = listGit[position].full_name
        holder.itemView.setOnClickListener {
            dialog.openDialog(context, "${listGit[position].name}","${listGit[position].full_name}")
        }

    }

    override fun getItemCount(): Int {
        return listGit.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitHubListItem>){
        listGit = list
        notifyDataSetChanged()
    }


}