package com.truckitin.truckitinmobileassignment.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.truckitin.truckitinmobileassignment.MOVIES_LIST_STARTING_PAGE
import com.truckitin.truckitinmobileassignment.SECRET_KEY
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.io.InvalidObjectException


class MoviesPagingSource(
    private val serviceMovies: MoviesApiService,
    private val category: String?,
    private val language: String?
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: MOVIES_LIST_STARTING_PAGE
        return try {
            val response = if (category != null && language != null) {
                serviceMovies.getMovies(category, SECRET_KEY, language, page)
            } else {
                throw InvalidObjectException("Category and Language should not be null!")
            }

            Timber.d("Movies response is: ${response.results}")

            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (page == MOVIES_LIST_STARTING_PAGE) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}