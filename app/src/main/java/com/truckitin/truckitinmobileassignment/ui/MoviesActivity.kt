package com.truckitin.truckitinmobileassignment.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.truckitin.truckitinmobileassignment.BR
import com.truckitin.truckitinmobileassignment.R
import com.truckitin.truckitinmobileassignment.databinding.ActivityMoviesBinding
import com.truckitin.truckitinmobileassignment.utils.ThemeSettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_shimmer.*

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        binding.lifecycleOwner = this
        viewModel.setUpNightMode()
        loadThemeSettings()
        viewModel.animateShimmer.set(true)
        viewModel.showErrorView.set(true)
        viewModel.getMoviesList()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    private fun loadThemeSettings() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, ThemeSettingsFragment())
            .commit()
    }
}