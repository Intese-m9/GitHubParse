package com.example.githubparse.domain.adapter.adapterCallBack

import com.example.githubparse.data.models.callbackexample.CallBackListItem

interface OnItemClickListener {
    fun onclick(item: CallBackListItem)
}