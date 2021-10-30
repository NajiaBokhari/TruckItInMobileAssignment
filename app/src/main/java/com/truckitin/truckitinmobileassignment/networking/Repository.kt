package com.truckitin.truckitinmobileassignment.networking

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class Repository constructor(
    private val serviceMovies: MoviesApiService
) {
    fun getMovieResultStream(category: String?, language: String?):
            Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    serviceMovies,
                    category,
                    language
                )
            }).flow
    }

}