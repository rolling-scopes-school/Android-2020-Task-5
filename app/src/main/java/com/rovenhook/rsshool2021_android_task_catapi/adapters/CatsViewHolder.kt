package com.rovenhook.rsshool2021_android_task_catapi.adapters

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.rovenhook.rsshool2021_android_task_catapi.R
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.CatItemBinding

class CatsViewHolder(
    private val binding: CatItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: CatsApiData, position: Int, context: Context) {

        binding.imageViewCat.load(cat.url) {
            crossfade(250)
            placeholder(R.drawable.temp_filler)
            error(R.drawable.temp_filler)
            transformations(RoundedCornersTransformation())
        }

        binding.imageViewCat.setOnClickListener {
            Toast.makeText( context, "position ${position}", Toast.LENGTH_SHORT).show()
        }
    }
}