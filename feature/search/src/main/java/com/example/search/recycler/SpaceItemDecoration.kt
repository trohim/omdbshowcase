package com.example.search.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

internal class SpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
        outRect.right = verticalSpaceHeight
        outRect.left = verticalSpaceHeight


        // Add top margin only for the first item to avoid double space between items  
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpaceHeight
        }
    }
}