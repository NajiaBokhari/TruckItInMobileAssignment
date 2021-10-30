package com.truckitin.truckitinmobileassignment.networking

import com.google.gson.annotations.SerializedName

data class MovieDTO(

    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("results") val results: List<Movie>
)
