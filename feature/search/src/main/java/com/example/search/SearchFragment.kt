package com.example.search

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.model.MovieInfo
import com.example.search.recycler.MoviesAdapter
import com.example.search.recycler.OnScrollListener
import com.example.search.recycler.SpaceItemDecoration
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class SearchFragment : Fragment(R.layout.search_screen) {

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var noResultTextView: TextView
    lateinit var navigateToDetails: () -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchEditText = view.findViewById<TextInputEditText>(R.id.textInputEditText)
        noResultTextView = view.findViewById(R.id.emptyTextView)
        recyclerView = view.findViewById(R.id.recyclerView)

        setupSearchEditText(searchEditText)
        setupRecyclerView(recyclerView)
        observeSearchResults()
    }

    private fun setupSearchEditText(searchEditText: TextInputEditText) {
        searchEditText.setText(viewModel.lastSearchQuery)
        searchEditText.addTextChangedListener { text ->
            text?.let {
                if (it.isNotBlank()) {
                    viewModel.search(it.toString())
                }
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val moviesAdapter = MoviesAdapter(mediaItems = listOf(),
            onItemSelected = viewModel::onItemSelected,
            onItemClickListener = {
                viewModel.onItemSelected(it)
                navigateToDetails()
            })

        recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.item_margin)))
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            addOnScrollListener(OnScrollListener(snapHelper) { position ->
                moviesAdapter.selectItem(position)
            })
        }
    }

    private fun observeSearchResults() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResultUiState.collectLatest { data ->
                    handleSearchResult(data)
                }
            }
        }
    }

    private fun handleSearchResult(data: SearchResultUiState) {
        when (data) {
            is SearchResultUiState.Success -> {
                updateMoviesAdapter(data.movies)
                noResultTextView.visibility = if (data.isEmpty()) View.VISIBLE else View.GONE
            }

            is SearchResultUiState.EmptyQuery -> {
                updateMoviesAdapter(emptyList())
                noResultTextView.visibility = View.GONE
            }

            else -> {}
        }
    }

    private fun updateMoviesAdapter(movies: List<MovieInfo>) {
        (recyclerView.adapter as? MoviesAdapter)?.updateItems(movies)
    }
}