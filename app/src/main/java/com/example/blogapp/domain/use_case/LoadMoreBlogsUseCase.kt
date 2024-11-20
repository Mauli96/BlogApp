package com.example.blogapp.domain.use_case

import com.example.blogapp.domain.model.BlogPost
import com.example.blogapp.domain.repository.BlogRepository

class LoadMoreBlogsUseCase(
    private val repository: BlogRepository
) {

    suspend operator fun invoke(): List<BlogPost> {
        return repository.loadMoreBlogs()
    }
}