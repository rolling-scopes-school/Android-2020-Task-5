package com.omelchenkoaleks.thecatapi.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.omelchenkoaleks.thecatapi.R
import com.omelchenkoaleks.thecatapi.data.Cat
import com.omelchenkoaleks.thecatapi.databinding.ItemCatBinding
import com.omelchenkoaleks.thecatapi.ui.listener.ItemClickListener

class CatViewHolder(
    private val itemClickListener: ItemClickListener?,
    binding: ItemCatBinding,
    var context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private var cat: Cat? = null
    private val imgCat = binding.imgCat

    fun bind(cat: Cat?) {
        this.cat = cat

        Glide.with(context)
            .load(cat?.url)
            .placeholder(R.drawable.default_cat)
            .optionalTransform(CenterCrop())
            .into(imgCat)

        itemView.setOnClickListener {
            itemClickListener?.onItemClick(cat?.url)
        }
    }
}
