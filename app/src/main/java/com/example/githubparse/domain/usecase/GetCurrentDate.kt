package com.example.githubparse.domain.usecase

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class GetCurrentDate {
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        return sdf.format(Calendar.getInstance().time)
    }
}