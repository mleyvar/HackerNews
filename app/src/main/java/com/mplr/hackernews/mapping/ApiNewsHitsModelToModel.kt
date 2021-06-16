package com.mplr.hackernews.mapping

import com.mplr.hackernews.models.ApiHitModel
import com.mplr.hackernews.models.ApiNewsHitsModel
import com.mplr.hackernews.models.HitModel
import com.mplr.hackernews.models.NewsHitsModel
import com.mplr.hackernews.persistence.NewsHitsEntity

internal fun ApiNewsHitsModel.toModel(): NewsHitsModel {
    return NewsHitsModel(
        apiHitModelToModel(hits),
        page,
        totalPages,
        hitsPerPage
    )
}

internal fun apiHitModelToModel(hits: MutableList<ApiHitModel>):  MutableList<HitModel> {
    val listNewsHits: MutableList<HitModel> = mutableListOf()

    hits.map {apiHitModel ->
        listNewsHits.add(HitModel(
            apiHitModel.objectId,
            apiHitModel?.title,
            apiHitModel?.author,
            apiHitModel?.storyUrl,
            apiHitModel?.url,
            apiHitModel?.storyTitle,
            apiHitModel?.commentText
        ))
    }
    return listNewsHits
}

internal fun newsHitsEntityToModel(hits: MutableList<NewsHitsEntity>):  MutableList<HitModel> {
    val listNewsHits: MutableList<HitModel> = mutableListOf()

    hits.map {apiHitModel ->
        listNewsHits.add(HitModel(
            apiHitModel.objectId,
            apiHitModel?.title,
            apiHitModel?.author,
            apiHitModel?.storyUrl,
            apiHitModel?.url,
            apiHitModel?.storyTitle,
            apiHitModel?.commentText
        ))
    }
    return listNewsHits
}
