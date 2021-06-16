package com.mplr.hackernews.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsHitsModelDao {

    @Query("INSERT INTO news_hits_deleted(objectId) VALUES(:objectId)")
    fun insertNewsHitsDeleted(objectId: String): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsHits(entity: NewsHitsEntity): Long

    @Query("DELETE FROM news_hits")
    fun deleteNewsHits()

    @Query("SELECT * FROM news_hits ")
    fun getNewsHits(): MutableList<NewsHitsEntity>

    @Query("SELECT objectId FROM news_hits_deleted  ")
    fun getNewsHitsStatusDelete(): List<String>

    @Update
    fun updateNewHit(newsHitsEntity: NewsHitsEntity)

}