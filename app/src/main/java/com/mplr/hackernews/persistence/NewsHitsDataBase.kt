package com.mplr.hackernews.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsHitsEntity::class, NewsHitsDeletedEntity::class], version = 2)
abstract class NewsHitsDataBase : RoomDatabase() {

    abstract fun provideNewsHitsDataBase(): NewsHitsModelDao
}
