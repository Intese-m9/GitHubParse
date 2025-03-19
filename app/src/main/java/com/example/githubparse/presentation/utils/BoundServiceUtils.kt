package com.example.githubparse.presentation.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.githubparse.presentation.service.BoundService
import javax.inject.Inject

class BoundServiceUtils @Inject constructor(val context: Context) {
    private var myService: BoundService? =
        null//ссылка на экземпляр сервиса после успешной установки связи
    private var isBound = false//логическая переменная связан ли сервис с компонентом или нет
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            //связь с сервисом установлена
            val binder =
                service as BoundService.MyBinder// приведение типа IBinder к нашему кастомнуму биндеру сервиса
            myService = binder.getService() //получение экземпляра
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName?) {
            //связь с сервисом разорвана
            isBound = false
        }
    }

    fun bindService() {
        Intent(context, BoundService::class.java).also { intent ->
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)//подключение к сервису
        }
    }

    fun unbindService() {

        when (isBound) {
            true -> {
                context.unbindService(connection)
                isBound = false

            }

            false -> {
                println("Сервис отключен")
            }
        }

    }

   suspend fun performAction(item:(Int) -> Unit) {
        myService?.setAction { id->
            item(id)
        }
    }
}