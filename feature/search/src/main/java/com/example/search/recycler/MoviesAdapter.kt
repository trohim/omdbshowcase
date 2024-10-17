package com.example.search.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.model.MovieInfo
import com.example.search.R
import com.google.android.material.card.MaterialCardView

internal class MoviesAdapter(
    private var mediaItems: List<MovieInfo>,
    val onItemClickListener: (MovieInfo) -> Unit,
    val onItemSelected: (MovieInfo) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MediaViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(mediaItems[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = mediaItems.size

    fun updateItems(newItems: List<MovieInfo>) {
        mediaItems = newItems
        notifyDataSetChanged()
    }

    fun selectItem(position: Int) {
        onItemSelected(mediaItems[position])
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val releaseYearTextView: TextView = itemView.findViewById(R.id.releaseYearTextView)

        fun bind(mediaItem: MovieInfo, isSelected: Boolean) {
            itemView.setOnClickListener { onItemClickListener(mediaItem) }
            (itemView as MaterialCardView).elevation = if (isSelected) 50f else 2f
            titleTextView.text = mediaItem.title
            releaseYearTextView.text = mediaItem.releaseYear

            posterImageView.load(mediaItem.posterUrl) {
                placeholder(R.drawable.baseline_image_24)
                error(R.drawable.baseline_image_24)
            }

            itemView.scaleX = if (isSelected) 1.03f else 1.0f
            itemView.scaleY = if (isSelected) 1.03f else 1.0f
        }
    }
}