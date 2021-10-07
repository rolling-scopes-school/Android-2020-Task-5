package com.omelchenkoaleks.thecatapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.omelchenkoaleks.thecatapi.data.Cat
import com.omelchenkoaleks.thecatapi.databinding.ItemCatBinding
import com.omelchenkoaleks.thecatapi.ui.listener.ItemClickListener

class CatAdapter(
    private val itemClickListener: ItemClickListener?
) : PagingDataAdapter<Cat, CatViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(itemClickListener, binding, parent.context)
    }


}
