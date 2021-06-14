package com.mplr.hackernews.api

import com.mplr.hackernews.models.ApiNewsHitsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoreHomeApi {

    @GET("/api/v1/search_by_date")
    @Headers("Content-Type: application/json ")
    fun getHits(
        @Query("query") query: String = QUERY_HITS,
        @Query("page") page: Int = 0
    ): Observable<ApiNewsHitsModel>


}