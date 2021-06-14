package com.mplr.hackernews.models

import com.google.gson.annotations.SerializedName

data class ApiNewsHitsModel (
    @SerializedName("hits") val hits: MutableList<ApiHitModel> = mutableListOf(),
    @SerializedName("page") val page: Int = 0,
    @SerializedName("nbPages") val totalPages: Int = 0,
    @SerializedName("hitsPerPage") val hitsPerPage: Int = 0
)