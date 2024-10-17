package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView

@Composable
internal fun SearchScreen(modifier: Modifier = Modifier, navigateToDetail: () -> Unit) {
    AndroidView(modifier = modifier, factory = { context ->
        FragmentContainerView(context).apply {
            id = R.id.fragment_container_view_id
            val fragment = SearchFragment().apply {
                navigateToDetails = navigateToDetail
            }
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            fragmentManager.beginTransaction().replace(id, fragment).commit()
        }
    })
}