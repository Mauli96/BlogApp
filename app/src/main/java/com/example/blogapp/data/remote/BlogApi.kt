package com.example.blogapp.data.remote

import com.example.blogapp.domain.model.BlogPost
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApi {

    @GET("posts")
    suspend fun getPosts(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): List<BlogPost>
}
