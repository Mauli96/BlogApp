package com.example.blogapp.data.repository

import com.example.blogapp.data.local.BlogDao
import com.example.blogapp.data.remote.BlogApi
import com.example.blogapp.domain.model.BlogPost
import com.example.blogapp.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val blogDao: BlogDao,
    private val blogApi: BlogApi
) : BlogRepository {

    private var currentPage = 1

    override suspend fun getCachedBlogs(): List<BlogPost> {
        return blogDao.getBlogPosts()
    }

    override suspend fun loadMoreBlogs(): List<BlogPost> {
        val newBlogs = blogApi.getPosts(
            perPage = 10,
            page = currentPage
        )
        blogDao.insertBlogPost(newBlogs)
        currentPage++
        return newBlogs
    }
}