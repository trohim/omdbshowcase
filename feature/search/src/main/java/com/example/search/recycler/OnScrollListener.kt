package com.example.search.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

internal class OnScrollListener(
    private val snapHelper: SnapHelper,
    private val onSelected: (Int) -> Unit
) :
    RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            snapHelper.findSnapView(recyclerView.layoutManager)?.let {
                val snapPosition = layoutManager.getPosition(it)
                onSelected(snapPosition)
            }
        }
    }
}
