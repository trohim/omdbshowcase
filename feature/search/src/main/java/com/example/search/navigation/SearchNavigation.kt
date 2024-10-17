package com.example.search.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.search.SearchScreen

const val SEARCH_ROUTE = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) =
    navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen(
    onMovieClick: () -> Unit,
) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(
            Modifier.fillMaxSize(), onMovieClick
        )
    }
}