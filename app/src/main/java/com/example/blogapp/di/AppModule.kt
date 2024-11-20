package com.example.blogapp.di

import androidx.room.Room
import com.example.blogapp.data.local.BlogDao
import com.example.blogapp.data.local.BlogDatabase
import com.example.blogapp.data.remote.BlogApi
import com.example.blogapp.data.repository.BlogRepositoryImpl
import com.example.blogapp.domain.repository.BlogRepository
import com.example.blogapp.domain.use_case.GetBlogsUseCase
import com.example.blogapp.domain.use_case.LoadMoreBlogsUseCase
import com.example.blogapp.presentation.BlogViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<BlogApi> {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlogApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            BlogDatabase::class.java,
            "blog_database"
        ).build()
    }

    single<BlogDao> {
        get<BlogDatabase>().blogDao()
    }

    single<BlogRepository> {
        BlogRepositoryImpl(
            blogDao = get(),
            blogApi = get()
        )
    }

    factory {
        GetBlogsUseCase(repository = get())
    }
    factory {
        LoadMoreBlogsUseCase(repository = get())
    }

    viewModel {
        BlogViewModel(
            getBlogsUseCase = get(),
            loadMoreBlogsUseCase = get()
        )
    }
}