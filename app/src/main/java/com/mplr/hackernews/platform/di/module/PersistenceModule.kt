package com.mplr.hackernews.platform.di.module

import android.content.Context
import androidx.room.Room
import com.mplr.hackernews.persistence.NewsHitsDataBase
import com.mplr.hackernews.persistence.NewsHitsModelDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

const val DATABASE_HOME = "news-hits-preference-db"

@Module
@InstallIn(ActivityComponent::class)
class PersistenceModule {

    @Provides
    @Reusable
    fun provideNewsHitsDataBase(@ApplicationContext context: Context): NewsHitsDataBase {
        return Room.databaseBuilder(
            context, NewsHitsDataBase::class.java,
            DATABASE_HOME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideNewsHitsModelDao(newsHitsDataBase: NewsHitsDataBase): NewsHitsModelDao {
        return newsHitsDataBase.provideNewsHitsDataBase()
    }
}