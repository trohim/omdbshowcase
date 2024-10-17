package com.example.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.details.DetailNavigation
import com.example.details.DetailsViewModel

const val DETAILS_ROUTE = "details_route"

fun NavController.navigateToDetails(navOptions: NavOptions? = null) =
    navigate(DETAILS_ROUTE, navOptions)

fun NavGraphBuilder.detailsScreen(
    preInitViewModel: DetailsViewModel? = null
) {
    composable(route = DETAILS_ROUTE) {
        DetailNavigation(preInitViewModel)
    }
}