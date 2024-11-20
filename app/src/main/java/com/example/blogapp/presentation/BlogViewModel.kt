package com.example.blogapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogapp.domain.model.BlogPost
import com.example.blogapp.domain.use_case.GetBlogsUseCase
import com.example.blogapp.domain.use_case.LoadMoreBlogsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel(
    private val getBlogsUseCase: GetBlogsUseCase,
    private val loadMoreBlogsUseCase: LoadMoreBlogsUseCase
) : ViewModel() {

    private val _blogs = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogs: StateFlow<List<BlogPost>> get() = _blogs

    init {
        viewModelScope.launch {
            _blogs.value = getBlogsUseCase()
        }
    }

    fun loadMoreBlogs() {
        viewModelScope.launch {
            val newBlogs = loadMoreBlogsUseCase()
            _blogs.value = _blogs.value + newBlogs
        }
    }
}
