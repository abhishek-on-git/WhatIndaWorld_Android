package com.wcp.whatindaworld.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wcp.whatindaworld.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsArticleDatabase: RoomDatabase() {
    abstract fun fetchArticleDAO(): NewsArticleDao
}