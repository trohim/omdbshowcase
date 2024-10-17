package com.example.details

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.model.MovieDetails

@Composable
internal fun DetailNavigation(
    preInitViewModel: DetailsViewModel? = null
) {
    val viewModel = preInitViewModel ?: hiltViewModel<DetailsViewModel>()
    val state by viewModel.uiState.collectAsState()

    when (val currentState = state) {
        is DetailsUiState.Success -> DetailScreen(currentState.movieDetails)
        else -> {}
    }
}

@Composable
internal fun DetailScreen(details: MovieDetails) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeLayout(details)
    } else {
        PortraitLayout(details)
    }
}

@Composable
private fun LandscapeLayout(details: MovieDetails) {
    Row(
        Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Poster(
            url = details.posterUrl, modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
        )
        Description(
            title = details.title,
            release = details.releaseYear,
            production = details.productionYear,
            details = details.plotSummary
        )
    }
}

@Composable
private fun PortraitLayout(details: MovieDetails) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Poster(
            url = details.posterUrl, modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Description(
            title = details.title,
            release = details.releaseYear,
            production = details.productionYear,
            details = details.plotSummary
        )
    }
}

@Composable
private fun Poster(url: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(model = url,
        contentDescription = "Movie Poster",
        contentScale = ContentScale.FillWidth,
        modifier = modifier,
        loading = {
            LoadingBox(stringResource(R.string.loading))
        },
        error = {
            ErrorBox(stringResource(R.string.failed_to_load_image))
        })
}

@Composable
private fun LoadingBox(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.Black)
    ) {
        Text(message, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ErrorBox(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.Red)
    ) {
        Text(message, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun Description(
    title: String,
    release: String,
    production: String,
    details: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = stringResource(R.string.title, title))
        Text(text = stringResource(R.string.release_date, release))
        Text(text = stringResource(R.string.production_date, production))
        Text(text = details)
    }
}