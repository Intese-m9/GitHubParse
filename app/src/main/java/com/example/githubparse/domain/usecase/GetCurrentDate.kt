package com.example.githubparse.domain.usecase

import java.text.SimpleDateFormat
import java.util.*

class GetCurrentDate {

    fun getCurrentDate(): String {
        val currentTime: Date = Calendar.getInstance().getTime()
        return currentTime.toString()
    }
}