package com.truckitin.truckitinmobileassignment.networking

import com.truckitin.truckitinmobileassignment.CATEGORY_TOP_RATED
import com.truckitin.truckitinmobileassignment.DEFAULT_LANGUAGE
import com.truckitin.truckitinmobileassignment.SECRET_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val serviceMovies: MoviesApiService) {

    suspend fun getTopRatedMovies(): ResponseResult<MovieDTO> {
        return withContext(Dispatchers.IO) {
            try {
                val moviesList = serviceMovies.getMovies(
                    CATEGORY_TOP_RATED,
                    SECRET_KEY,
                    DEFAULT_LANGUAGE,
                    2,
                    false
                )
                 Timber.d("success: $moviesList")
                ResponseResult.Success(moviesList)

            } catch (exception: IOException) {
                Timber.d("error: $exception")
                ResponseResult.Error(exception)
            } catch (exception: HttpException) {
                Timber.d("error: $exception")
                ResponseResult.Error(exception)
            }
        }
    }

}