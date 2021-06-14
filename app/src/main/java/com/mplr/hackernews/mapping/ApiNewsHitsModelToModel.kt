package com.mplr.hackernews.mapping

import com.mplr.hackernews.models.ApiNewsHitsModel
import com.mplr.hackernews.models.HitModel
import com.mplr.hackernews.models.NewsHitsModel

internal fun ApiNewsHitsModel.toModel(): NewsHitsModel {
    return NewsHitsModel(
        hits as MutableList<HitModel>,
        page,
        totalPages,
        hitsPerPage
    )
}