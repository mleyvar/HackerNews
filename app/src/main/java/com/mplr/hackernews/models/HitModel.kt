package com.mplr.hackernews.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class HitModel(
    val objectId: String = "",
    val title: String? = null,
    val author: String?  = null,
    val storyUrl: String? = null,
    val url: String? = null,
    val storyTitle: String? = null,
    val commentText: String? = null
) : Parcelable {}