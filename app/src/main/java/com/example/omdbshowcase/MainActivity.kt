package com.example.omdbshowcase

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.example.data.utils.NetworkMonitor
import com.example.omdbshowcase.ui.OmdbApp
import com.example.omdbshowcase.ui.theme.OmdbshowcaseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmdbshowcaseTheme {
                OmdbApp(networkMonitor)
            }
        }

    }
}
