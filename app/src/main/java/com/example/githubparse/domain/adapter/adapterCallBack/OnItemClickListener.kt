package com.example.githubparse.domain.adapter.adapterCallBack

import com.example.githubparse.domain.models.callbackexample.CallBackListItem

interface OnItemClickListener {
    fun onclick(item:CallBackListItem)
}