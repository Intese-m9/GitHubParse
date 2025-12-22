package com.example.githubparse.domain.adapter.adaptercalendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparse.data.room.GitUserDownloadEntity
import com.example.githubparse.databinding.ItemListBinding
import com.example.githubparse.domain.usecase.GetCurrentDate

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {
    var dataList = emptyList<GitUserDownloadEntity>()
    private val dataGetString by lazy { GetCurrentDate() }//Use_case получение даты

    class CalendarViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        with(holder.binding) {
            val currentDate = dataGetString.getCurrentDate()
            val currentPosition = dataList[position].data
            println(currentPosition)
            if (currentDate == currentPosition) {
                holder.binding.user.text = dataList[position].repo
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GitUserDownloadEntity>) {
        dataList = list
        notifyDataSetChanged()
    }

}