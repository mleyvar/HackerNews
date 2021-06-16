package com.mplr.hackernews.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mplr.hackernews.logger.Logger
import com.mplr.hackernews.models.HitModel
import com.mplr.hackernews.models.NewsHitsModel
import com.mplr.hackernews.persistence.NewsHitsEntity
import com.mplr.hackernews.persistence.NewsHitsModelDao
import com.mplr.hackernews.repositories.NewsHitsRepository
import com.mplr.hackernews.utils.getOrAwaitValue
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsHitsViewModelTest {

    private lateinit var newsHitsViewModel: NewsHitsViewModel

    @Mock
    private lateinit var newsHitsRepository: NewsHitsRepository

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var newHitsModelDao: NewsHitsModelDao

    private lateinit var newsHitsModel: NewsHitsModel

    private lateinit var newsHitsEntity: MutableList<NewsHitsEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        setUpRxSchedulers()
        MockitoAnnotations.initMocks(this)
        initObjectMock()
        initControllers()
    }

    private fun initObjectMock() {
        var newsHitsEntityMock: MutableList<NewsHitsEntity> = mutableListOf()
        var hitsMock: MutableList<HitModel> = mutableListOf()

        newsHitsEntity = mutableListOf()
        for (i in 1..10) {
            newsHitsEntity.add(
                NewsHitsEntity(
                    objectId = i.toString(),
                    title = "Title mock $i",
                    author = "Author mock $i",
                    storyUrl = "url mock $i"
                )
            )

            hitsMock.add(
                HitModel(
                    objectId = i.toString(),
                    title = "Title mock $i",
                    author = "Author mock $i",
                    storyUrl = "url mock $i"
                )
            )
        }

        newsHitsModel = NewsHitsModel(
            hits = hitsMock,
            page = 0,
            totalPages = 1,
            hitsPerPage = 10
        )
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(newsHitsRepository.getHits()).thenReturn(
            Observable.just(
                newsHitsModel
            )
        )

        whenever(newHitsModelDao.getNewsHits()).thenReturn(
            newsHitsEntity
        )
    }

    private fun initializaNewsHitsViewModel() {

        newsHitsViewModel = NewsHitsViewModel(
            newsHitsRepository,
            logger,
            newHitsModelDao
        )
    }

    @Test
    fun `When call news then return one page with twenty news `() {
        initializaNewsHitsViewModel()

        newsHitsViewModel.getHits()

        Assert.assertEquals(
            newsHitsModel.hits.size,
            newsHitsViewModel.listNewsHits.getOrAwaitValue().hits.size
        )
        verify(newsHitsRepository).getHits()
    }
}
