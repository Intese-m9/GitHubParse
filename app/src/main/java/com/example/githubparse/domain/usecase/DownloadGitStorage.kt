package com.example.githubparse.domain.usecase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
class DownloadGitStorage {

    fun execute(context: Context, url:String) {
        var intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://api.github.com/repos/$url/zipball/master"))
        context.startActivity(intent)
    }


}