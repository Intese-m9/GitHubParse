package com.example.githubparse.domain.usecase

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.example.githubparse.R
import com.example.githubparse.domain.models.GitHubItem
import com.example.githubparse.presentation.viewmodel.ViewModelActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.graphics.toColorInt
import androidx.core.graphics.drawable.toDrawable

class Dialog @Inject constructor(private val viewModelActivity: ViewModelActivity) {
    private val openGitBrowser by lazy { OpenGitBrowser() }//Use_Case открытия странички в интернете
    private val downloadGitBrowser by lazy { DownloadGitStorage() }//Use_Case загрузка
    private val dataGetString by lazy { GetCurrentDate() }//Use_case получение даты
    fun openDialog(context: Context, itemPositionName:String,itemPositionFullName:String ){

        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle(itemPositionName)
        builder.setIcon(R.drawable.ic_baseline_help_outline_24)
        builder.setMessage("Вы можете скачать или открыть данный файл в браузере")
        builder.background = "#FEFEFA".toColorInt().toDrawable()
        builder.setPositiveButton("Download"){ _, _ ->
            //действие по клику
            downloadGitBrowser.execute(context, itemPositionFullName)
            val data = dataGetString.getCurrentDate()
            CoroutineScope(Dispatchers.Default).launch {
                viewModelActivity.insertGitUserInDataBase(GitHubItem(0, itemPositionFullName, data))
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