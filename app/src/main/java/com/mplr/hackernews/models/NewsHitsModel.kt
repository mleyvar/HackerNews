package com.mplr.hackernews.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsHitsModel(
    var hits: MutableList<HitModel> = mutableListOf(),
    val page: Int = 0,
    val totalPages: Int = 0,
    val hitsPerPage: Int = 0
) : Parcelable {
}