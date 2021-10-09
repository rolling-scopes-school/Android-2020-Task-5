package com.rovenhook.rsshool2021_android_task_catapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.CatItemBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnSmallImageClickListener

class CatsAdapter(
    private val onSmallImageClickListener: OnSmallImageClickListener
) : ListAdapter<CatsApiData, CatsViewHolder>(itemComporator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater)
        return CatsViewHolder(binding, onSmallImageClickListener)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val itemComporator = object : DiffUtil.ItemCallback<CatsApiData>() {
            override fun areItemsTheSame(oldItem: CatsApiData, newItem: CatsApiData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatsApiData, newItem: CatsApiData): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}
