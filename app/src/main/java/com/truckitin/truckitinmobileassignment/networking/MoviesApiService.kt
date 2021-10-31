package com.truckitin.truckitinmobileassignment.networking

import com.truckitin.truckitinmobileassignment.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/{category}")
    suspend fun getMovies(
        @Path(CATEGORY) category: String,
        @Query(API_KEY) apiKey: String,
        @Query(LANGUAGE) language: String,
        @Query(PAGE) page: Int,
        @Query(ADULT) adult: Boolean
        ): MovieDTO
}