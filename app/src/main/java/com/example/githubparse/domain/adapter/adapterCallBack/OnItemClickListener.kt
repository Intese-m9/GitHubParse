package com.example.githubparse.domain.adapter.adapterCallBack

import com.example.githubparse.data.models.callbackexample.CallBackListItemDTO

interface OnItemClickListener {
    fun onclick(item: CallBackListItemDTO)
}