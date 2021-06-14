package com.mplr.hackernews.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mplr.hackernews.logger.Logger
import com.mplr.hackernews.repositories.NewsHitsRepository
import com.mplr.hackernews.utils.className
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class NewsHitsViewModel @ViewModelInject constructor(
    var newsHitsRepository: NewsHitsRepository,
    private val logger: Logger

) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getHits(page: Int = 0) {

        compositeDisposable += newsHitsRepository.getHits(
            page = page
        )
            .subscribeOn(Schedulers.io())
            .subscribe({
                logger.d("SAMPLE","DATA SERVICE: " + it.hits.size.toString())
            }, {
                logger.e(className(), it.message.orEmpty(), it)
            })

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}