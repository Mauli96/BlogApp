package com.example.blogapp.data.local

import androidx.room.TypeConverter
import com.example.blogapp.domain.model.Content
import com.example.blogapp.domain.model.Title
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BlogPostTypeConverters {
    @TypeConverter
    fun fromTitle(title: Title): String {
        val gson = Gson()
        return gson.toJson(title)
    }

    @TypeConverter
    fun toTitle(titleString: String): Title {
        val gson = Gson()
        val type = object : TypeToken<Title>() {}.type
        return gson.fromJson(titleString, type)
    }

    @TypeConverter
    fun fromContent(content: Content): String {
        val gson = Gson()
        return gson.toJson(content)
    }

    @TypeConverter
    fun toContent(contentString: String): Content {
        val gson = Gson()
        val type = object : TypeToken<Content>() {}.type
        return gson.fromJson(contentString, type)
    }
}