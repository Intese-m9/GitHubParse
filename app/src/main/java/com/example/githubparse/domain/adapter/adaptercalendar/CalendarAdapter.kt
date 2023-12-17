package com.example.githubparse.domain.adapter.adaptercalendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.R
import com.example.githubparse.data.room.GitUser
import com.example.githubparse.domain.usecase.GetCurrentDate
import kotlinx.android.synthetic.main.item_list.view.*

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    var dataList = emptyList<GitUser>()
    private val dataGetString by lazy { GetCurrentDate() }//Use_case получение даты

    class CalendarViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CalendarViewHolder(view)
    }
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val currentDate = dataGetString.getCurrentDate()
        val currentPosition = dataList[position].data
        println(currentPosition)
        if (currentDate == currentPosition){
            holder.itemView.user.text = dataList[position].repo
        }else{

        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitUser>){
        dataList = list
        notifyDataSetChanged()
    }

}