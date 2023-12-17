package com.example.githubparse.domain.usecase

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.githubparse.R
import com.example.githubparse.data.room.GitDao
import com.example.githubparse.data.room.GitDatabase
import com.example.githubparse.data.room.GitUser
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Dialog {
    private val openGitBrowser by lazy { OpenGitBrowser() }//Use_Case открытия странички в интернете
    private val downloadGitBrowser by lazy { DownloadGitStorage() }//Use_Case загрузка
    private val dataGetString by lazy { GetCurrentDate() }//Use_case получение даты
    private lateinit var gitDao: GitDao
    fun openDialog(context: Context, itemPositionName:String,itemPositionFullName:String ){
        val db = Room.databaseBuilder(
            context,
            GitDatabase::class.java, "repository_database"
        ).build()//создаем базу данных
        gitDao = db.GitDao()//получили экземпляр бд
        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle(itemPositionName)
        builder.setIcon(R.drawable.ic_baseline_help_outline_24)
        builder.setMessage("Вы можете скачать или открыть данный файл в браузере")
        builder.background = ColorDrawable(
            Color.parseColor("#FEFEFA")
        )
        builder.setPositiveButton("Download"){ _, _ ->
            //действие по клику
            downloadGitBrowser.execute(context, itemPositionFullName)
            val data = dataGetString.getCurrentDate()
            CoroutineScope(Dispatchers.Default).launch {
                gitDao.insertRepo(GitUser(0,itemPositionFullName,data))
            }
        }
        val drawablePositive = ContextCompat.getDrawable(
            context, R.drawable.ic_baseline_cloud_download_24)
        builder.setPositiveButtonIcon(
            drawablePositive
        )
        builder.setNegativeButton("Открыть в браузере"){ _, _ ->
            openGitBrowser.execute(context,itemPositionFullName)
        }
        val drawableNegative = ContextCompat.getDrawable(
            context, R.drawable.ic_baseline_open_in_browser_24)
        builder.setNegativeButtonIcon(
            drawableNegative
        )
        builder.setNeutralButton("Отмена"){ dialog, _ ->
            // do something on neutral button click
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }
}