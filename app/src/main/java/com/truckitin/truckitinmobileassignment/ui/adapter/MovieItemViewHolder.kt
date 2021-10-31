package com.truckitin.truckitinmobileassignment.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.truckitin.truckitinmobileassignment.databinding.ItemMovieBinding
import com.truckitin.truckitinmobileassignment.networking.Movie

class MovieItemViewHolder(
    private val itemMovieBinding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun onBind(
        movie: Movie
    ) {
        itemMovieBinding.movie = movie
        itemMovieBinding.executePendingBindings()
    }
}
