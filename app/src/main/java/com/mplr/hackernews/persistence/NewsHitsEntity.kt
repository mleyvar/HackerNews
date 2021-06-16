package com.mplr.hackernews.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_hits")
data class NewsHitsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    val objectId: String = "",
    val title: String? = null,
    val author: String?  = null,
    val storyUrl: String? = null,
    val url: String? = null,
    val storyTitle: String? = null,
    val commentText: String? = null
)