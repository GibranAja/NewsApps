package com.pplgskanic.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pplgskanic.newsapp.data.local.entity.ArticleDao
import com.pplgskanic.newsapp.data.local.entity.ArticleEntity
import com.pplgskanic.newsapp.data.local.entity.RemoteKeysArticle
import com.pplgskanic.newsapp.data.local.entity.RemoteKeysArticleDao

@Database(
    entities = [ArticleEntity::class, RemoteKeysArticle::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeysArticleDao(): RemoteKeysArticleDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java, "news_database"
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}