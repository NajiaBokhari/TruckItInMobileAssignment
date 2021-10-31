package com.truckitin.truckitinmobileassignment.utils

import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.truckitin.truckitinmobileassignment.R


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("recycleViewAdapter")

    fun setRecycleViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?
    ) {
        if (null == adapter)
            return
        recyclerView.adapter = adapter
    }

    @BindingAdapter("src")
    @JvmStatic
    fun setImageResId(view: AppCompatImageView, imageSrc: String ) {

            Glide.with(view.context)
                .load(imageSrc).transforms(CenterCrop(), RoundedCorners(25))
                .placeholder(R.drawable.no_image).transforms(CenterCrop(), RoundedCorners(25))
                .into(view)

    }
}