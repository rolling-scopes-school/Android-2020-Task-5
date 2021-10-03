package com.rovenhook.rsshool2021_android_task_catapi.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.rovenhook.rsshool2021_android_task_catapi.R
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.CatItemBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnSmallImageClickListener

class CatsViewHolder(
    private val binding: CatItemBinding,
    private val onSmallImageClickListener: OnSmallImageClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: CatsApiData) {
        binding.imageViewCat.load(cat.url) {
            crossfade(250)
            placeholder(R.drawable.temp_filler)
            error(R.drawable.temp_filler)
            transformations(RoundedCornersTransformation())
        }

        // click to enlarge the photo
        binding.imageViewCat.setOnClickListener {
            onSmallImageClickListener.onSmallImageClick(binding.imageViewCat)
        }
    }
}