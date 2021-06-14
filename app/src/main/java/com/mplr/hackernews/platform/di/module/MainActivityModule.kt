package com.mplr.hackernews.platform.di.module

import com.mplr.hackernews.api.CoreHomeApi
import com.mplr.hackernews.repositories.NewsHitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {

    @Provides
    fun newHitsRepositoryProvider(apiService: CoreHomeApi) = NewsHitsRepository(apiService)

}