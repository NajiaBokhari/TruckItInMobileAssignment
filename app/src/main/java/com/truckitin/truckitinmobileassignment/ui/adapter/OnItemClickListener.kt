package com.truckitin.truckitinmobileassignment.ui.adapter

import android.view.View


interface OnItemClickListener {
    fun onItemClick(view: View, data: Any, pos: Int)

    companion object {
        operator fun invoke(): OnItemClickListener {
            return object : OnItemClickListener {
                override fun onItemClick(view: View, data: Any, pos: Int) {

                }
            }
        }
    }
}