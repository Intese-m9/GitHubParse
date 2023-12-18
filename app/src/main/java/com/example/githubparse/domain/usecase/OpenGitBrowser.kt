package com.example.githubparse.domain.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenGitBrowser {
    fun execute(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/$url"))
        context.startActivity(intent)
    }
}