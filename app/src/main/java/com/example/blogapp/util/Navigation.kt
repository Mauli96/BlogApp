package com.example.blogapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.*
import com.example.blogapp.presentation.BlogScreen
import com.example.blogapp.presentation.BlogViewModel
import com.example.blogapp.presentation.WebViewScreen

@Composable
fun Navigation(
    viewModel: BlogViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val blogs by viewModel.blogs.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BlogList
    ) {
        // Blog List Screen
        composable<BlogList> {
            BlogScreen(
                blogs = blogs,
                onRefresh = {
                    viewModel.loadMoreBlogs()
                },
                onClick = {
                    navController.navigate(BlogScreen(it))
                }
            )
        }

        // Blog Details Screen (WebView)
        composable<BlogScreen> {
            val args = it.toRoute<BlogScreen>()

            WebViewScreen(args.link)
        }
    }
}

@Serializable
object BlogList

@Serializable
data class BlogScreen(
    val link: String
)