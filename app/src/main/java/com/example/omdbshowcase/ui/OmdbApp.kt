package com.example.omdbshowcase.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.data.utils.NetworkMonitor
import com.example.details.DetailsViewModel
import com.example.search.navigation.SEARCH_ROUTE
import com.example.search.navigation.detailsScreen
import com.example.search.navigation.navigateToDetails
import com.example.search.navigation.searchScreen

@Composable
fun OmdbApp(networkMonitor: NetworkMonitor) {
    val isConnected = networkMonitor.isOnline.collectAsState(false)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isConnected.value) {
            OmdbNavHost()
        } else {
            Text(text = "No internet connection", modifier = Modifier.align(Alignment.Center))
        }
    }

}


@Composable
fun OmdbNavHost() {
    val navController = rememberNavController()
    val activity = checkNotNull(LocalViewModelStoreOwner.current)
    val detailsViewModel = viewModel<DetailsViewModel>(activity)
    NavHost(
        navController = navController, startDestination = SEARCH_ROUTE
    ) {
        searchScreen { navController.navigateToDetails() }
        detailsScreen(detailsViewModel)
    }
}
