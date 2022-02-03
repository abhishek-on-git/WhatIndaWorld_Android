package com.wcp.whatindaworld.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wcp.whatindaworld.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM saved_articles")
    fun fetchSaveArticles(): Flow<List<Article>>

}