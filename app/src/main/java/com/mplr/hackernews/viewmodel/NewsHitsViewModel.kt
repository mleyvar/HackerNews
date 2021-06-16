package com.mplr.hackernews.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mplr.hackernews.logger.Logger
import com.mplr.hackernews.mapping.newsHitsEntityToModel
import com.mplr.hackernews.models.HitModel
import com.mplr.hackernews.models.NewsHitsModel
import com.mplr.hackernews.persistence.NewsHitsEntity
import com.mplr.hackernews.persistence.NewsHitsModelDao
import com.mplr.hackernews.repositories.NewsHitsRepository
import com.mplr.hackernews.utils.className
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsHitsViewModel @ViewModelInject constructor(
    var newsHitsRepository: NewsHitsRepository,
    private val logger: Logger,
    private val newHitsModelDao: NewsHitsModelDao

) : ViewModel() {

    val listNewsHits: MutableLiveData<NewsHitsModel> by lazy {
        MutableLiveData<NewsHitsModel>()
    }

    private val compositeDisposable = CompositeDisposable()

    fun getHits(page: Int = 0) {

        compositeDisposable += newsHitsRepository.getHits(
            page = page
        )
            .subscribeOn(Schedulers.io())
            .subscribe({ newHitsModel ->
                val itemsDelete = newHitsModelDao.getNewsHitsStatusDelete()

                newHitsModel.hits =
                    newHitsModel.hits.filterNot { hits -> itemsDelete.contains(hits.objectId) } as MutableList<HitModel>

                saveNewsDataBase(newHitsModel.hits)

                setItemsFromDataBaseToListNewsHits(newHitsModel)

            }, {
                logger.e(className(), it.message.orEmpty(), it)
                setItemsFromDataBaseToListNewsHits(NewsHitsModel())
            })
    }

    fun deleteItemDataBase(objectId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newHitsModelDao.insertNewsHitsDeleted(objectId)
        }
    }

    private fun setItemsFromDataBaseToListNewsHits(newHitsModel: NewsHitsModel) {
        listNewsHits.postValue(
            NewsHitsModel(
                newsHitsEntityToModel(
                    newHitsModelDao.getNewsHits()
                ),
                newHitsModel.page,
                newHitsModel.totalPages,
                newHitsModel.hitsPerPage
            )
        )
    }

    private fun saveNewsDataBase(newHitsModel: MutableList<HitModel>) {
        newHitsModelDao.deleteNewsHits()
        newHitsModel.map { hitModel ->
            newHitsModelDao.insertNewsHits(
                NewsHitsEntity(
                    objectId = hitModel.objectId,
                    title = hitModel.title,
                    author = hitModel.author,
                    storyUrl = hitModel.storyUrl,
                    url = hitModel.url,
                    storyTitle = hitModel.storyTitle,
                    commentText = hitModel.commentText
                )
            )
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}