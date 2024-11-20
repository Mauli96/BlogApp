package com.example.blogapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_posts")
data class BlogPost(
    @PrimaryKey val id: Int,
    val title: Title,
    val content: Content,
    val link: String
)

data class Title(val rendered: String)

data class Content(val rendered: String)