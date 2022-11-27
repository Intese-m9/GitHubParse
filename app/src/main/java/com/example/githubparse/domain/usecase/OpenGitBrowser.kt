package com.example.githubparse.domain.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class OpenGitBrowser {
    fun execute(context: Context,url:String) {
        var intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/$url"))
        context.startActivity(intent)
    }


}