package com.example.blogapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogapp.domain.model.BlogPost

@Dao
interface BlogDao {
    @Query("SELECT * FROM blog_posts")
    suspend fun getBlogPosts(): List<BlogPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogPost(blogPost: List<BlogPost>)
}