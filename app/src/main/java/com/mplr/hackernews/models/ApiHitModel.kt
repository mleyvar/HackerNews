package com.mplr.hackernews.models

import com.google.gson.annotations.SerializedName

data class ApiHitModel (
    @SerializedName("objectID") val objectId: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("story_url") val storyUrl: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("story_title") val storyTitle: String = "",
    @SerializedName("comment_text") val commentText: String = ""
)