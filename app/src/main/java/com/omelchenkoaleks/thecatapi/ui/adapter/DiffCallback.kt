package com.omelchenkoaleks.thecatapi.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omelchenkoaleks.thecatapi.data.Cat

object DiffCallback : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }

}
