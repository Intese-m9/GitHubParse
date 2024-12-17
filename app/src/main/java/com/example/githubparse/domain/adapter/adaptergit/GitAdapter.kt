package com.example.githubparse.domain.adapter.adaptergit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.databinding.ItemListBinding
import com.example.githubparse.domain.models.getlist.GitHubListItem
import javax.inject.Inject

class GitAdapter @Inject constructor(
    private val onItemClick:(GitHubListItem) -> Unit
) : RecyclerView.Adapter<GitAdapter.GitViewHolder>() {
    private var listGit = listOf<GitHubListItem>()

    class GitViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater)
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        with(holder.binding) {
            user.text = listGit[position].full_name
            holder.itemView.setOnClickListener {
                onItemClick(listGit[position])
            }
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