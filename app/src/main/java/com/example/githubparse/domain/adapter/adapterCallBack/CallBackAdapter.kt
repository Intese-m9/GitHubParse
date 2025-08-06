package com.example.githubparse.domain.adapter.adapterCallBack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.databinding.ItemListBinding
import com.example.githubparse.data.models.callbackexample.CallBackListItem

class CallBackAdapter(
    private val listGitTestCallBack: MutableList<CallBackListItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CallBackAdapter.CallBackViewHolder>() {
    class CallBackViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallBackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binder = ItemListBinding.inflate(inflater)
        return CallBackViewHolder(binder)
    }

    override fun getItemCount(): Int {
        return listGitTestCallBack.size
    }

    override fun onBindViewHolder(holder: CallBackViewHolder, position: Int) {
        with(holder.binding) {
            user.text = listGitTestCallBack[position].name
            holder.itemView.setOnClickListener {
                listener.onclick(listGitTestCallBack[position])
            }
        }
    }

}