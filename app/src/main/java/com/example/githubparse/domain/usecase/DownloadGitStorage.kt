package com.example.githubparse.domain.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
class DownloadGitStorage {

    fun execute(context: Context, url:String) {
        val intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://api.github.com/repos/$url/zipball/master"))
        context.startActivity(intent)
    }


}