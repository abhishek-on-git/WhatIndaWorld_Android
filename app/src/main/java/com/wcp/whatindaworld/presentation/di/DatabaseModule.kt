package com.wcp.whatindaworld.presentation.di

import android.app.Application
import androidx.room.Room
import com.wcp.whatindaworld.data.db.NewsArticleDao
import com.wcp.whatindaworld.data.db.NewsArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(app: Application): NewsArticleDatabase {
        return Room.databaseBuilder(app, NewsArticleDatabase::class.java,"article_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDAO(database: NewsArticleDatabase): NewsArticleDao {
        return database.fetchArticleDAO()
    }
}