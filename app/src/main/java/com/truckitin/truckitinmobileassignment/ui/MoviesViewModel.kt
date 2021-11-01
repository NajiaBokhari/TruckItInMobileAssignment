package com.truckitin.truckitinmobileassignment.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.truckitin.truckitinmobileassignment.NIGHT_MODE_KEY
import com.truckitin.truckitinmobileassignment.NIGHT_MODE_OFF
import com.truckitin.truckitinmobileassignment.NIGHT_MODE_ON
import com.truckitin.truckitinmobileassignment.networking.Movie
import com.truckitin.truckitinmobileassignment.networking.Repository
import com.truckitin.truckitinmobileassignment.networking.ResponseResult
import com.truckitin.truckitinmobileassignment.ui.adapter.MovieAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) :
    ViewModel() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModelJob = Job()
    var moviesList = MutableLiveData<List<Movie>>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var adapter: MovieAdapter = MovieAdapter(mutableListOf())
    var animateShimmer: ObservableField<Boolean> = ObservableField()
    var showErrorView: ObservableField<Boolean> = ObservableField()

    fun getMoviesList() {

        uiScope.launch {
            if (animateShimmer.get() != true) animateShimmer.set(true)
            showErrorView.set(false)
            when (val result = repository.getTopRatedMovies()) {
                is ResponseResult.Success -> {
                    result.data
                    moviesList.value = result.data.results
                    moviesList.value?.let { adapter?.setList(it) }
                    animateShimmer.set(false)
                }
                is ResponseResult.Error -> {
                    null
                    animateShimmer.set(false)
                    showErrorView.set(true)
                }

            }
        }
    }

    fun retry() {
        getMoviesList()
    }

    internal fun setUpNightMode() {
        val nightMode = sharedPreferences.getString(NIGHT_MODE_KEY, NIGHT_MODE_OFF)
        if (nightMode == NIGHT_MODE_ON) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }
}