package com.example.blogapp.domain.repository

import com.example.blogapp.domain.model.BlogPost

interface BlogRepository {
    suspend fun getCachedBlogs(): List<BlogPost>
    suspend fun loadMoreBlogs(): List<BlogPost>
}