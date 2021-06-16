package com.mplr.hackernews.repositories

import com.mplr.hackernews.api.CoreHomeApi
import com.mplr.hackernews.api.QUERY_HITS
import com.mplr.hackernews.mapping.toModel
import com.mplr.hackernews.models.NewsHitsModel
import io.reactivex.Observable
import javax.inject.Inject

class NewsHitsRepository @Inject constructor(
    private val apiService: CoreHomeApi
) {

    fun getHits(query: String = QUERY_HITS, page: Int = 0): Observable<NewsHitsModel> {
        return apiService.getHits(
            query = query,
            page = page  )
            .map {ApiNewsHitsModel ->
                ApiNewsHitsModel.toModel()
            }
    }

}