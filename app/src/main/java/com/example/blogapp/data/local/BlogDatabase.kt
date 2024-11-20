package com.example.blogapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.blogapp.domain.model.BlogPost

@Database(entities = [BlogPost::class], version = 1, exportSchema = false)
@TypeConverters(BlogPostTypeConverters::class)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        @Volatile
        private var INSTANCE: BlogDatabase? = null

        fun getDatabase(context: Context): BlogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlogDatabase::class.java,
                    "song_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}