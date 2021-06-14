package com.mplr.hackernews.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsHitsModel(
    val hits: MutableList<HitModel> = mutableListOf(),
    val page: Int = 0,
    val totalPages: Int = 0,
    val hitsPerPage: Int = 0
) : Parcelable {
}