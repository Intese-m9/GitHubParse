package com.example.githubparse.domain.adapter.adaptercalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.R
import com.example.githubparse.data.room.GitUser
import kotlinx.android.synthetic.main.item_list.view.*

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    var dataList = emptyList<GitUser>()
    class CalendarViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CalendarViewHolder(view)
    }
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.itemView.user.text = dataList[position].data
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setList(list: List<GitUser>){
        dataList = list
        notifyDataSetChanged()
    }

}