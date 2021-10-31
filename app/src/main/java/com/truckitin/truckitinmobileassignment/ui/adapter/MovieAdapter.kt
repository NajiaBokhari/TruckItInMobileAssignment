package com.truckitin.truckitinmobileassignment.ui.adapter

import androidx.databinding.ViewDataBinding
import com.truckitin.truckitinmobileassignment.R
import com.truckitin.truckitinmobileassignment.databinding.ItemMovieBinding
import com.truckitin.truckitinmobileassignment.networking.Movie

class MovieAdapter(
    private val list: MutableList<Movie>
) : BaseBindingRecyclerAdapter<Movie, MovieItemViewHolder>(
    list
) {
    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.item_movie

    override fun onCreateViewHolder(binding: ViewDataBinding): MovieItemViewHolder {
        return MovieItemViewHolder(
            binding as ItemMovieBinding
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position])
    }
}
