package com.mplr.hackernews.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HitModel(
    val objectId: String = "",
    val title: String = "",
    val author: String = "",
    val storyUrl: String = "",
    val url: String = "",
    val storyTitle: String = "",
    val commentText: String = ""
) : Parcelable {}