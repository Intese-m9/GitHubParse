package com.example.githubparse.presentation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.delay

class BoundService : Service() {
    private val binder = MyBinder()
    inner class MyBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    suspend fun setAction(item:(Int) -> Unit) {
        for (i in 1..100){
            delay(1000)
            item(i)
            println("$i")
        }
    }
}