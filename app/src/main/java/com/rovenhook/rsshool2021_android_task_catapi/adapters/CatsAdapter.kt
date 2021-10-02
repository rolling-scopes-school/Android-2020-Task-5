package com.rovenhook.rsshool2021_android_task_catapi.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.CatItemBinding

class CatsAdapter(
    val onReachEndListener: OnReachEndListener,
    val context: Context
) : ListAdapter<CatsApiData, CatsViewHolder>(itemComporator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(layoutInflater)
        return CatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        if (position == currentList.size - 3) {
            onReachEndListener.onReachEnd()
        }
        holder.bind(getItem(position), position, context)
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