package com.example.githubparse.domain.adapter.adaptergit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.R
import com.example.githubparse.domain.models.getlist.GitHubListItem
import com.example.githubparse.domain.usecase.Dialog
import kotlinx.android.synthetic.main.item_list.view.*
import javax.inject.Inject

class GitAdapter @Inject constructor(private val context: Context, private val dialog: Dialog) : RecyclerView.Adapter<GitAdapter.GitViewHolder>() {
    var listGit = emptyList<GitHubListItem>()
    class GitViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return GitViewHolder(view)
    }
    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.itemView.user.text = listGit[position].full_name
        holder.itemView.setOnClickListener {
            dialog.openDialog(context, listGit[position].name, listGit[position].full_name)
        }
    }
    override fun getItemCount(): Int {
        return listGit.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitHubListItem>) {
        listGit = list
        notifyDataSetChanged()
    }
}