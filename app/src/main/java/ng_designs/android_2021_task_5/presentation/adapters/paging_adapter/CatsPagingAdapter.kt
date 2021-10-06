package ng_designs.android_2021_task_5.presentation.adapters.paging_adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ng_designs.android_2021_task_5.domain.cat.models.Cats
import ng_designs.android_2021_task_5.presentation.adapters.AdapterDiffCallback
import ng_designs.android_2021_task_5.presentation.adapters.CatViewHolder


class CatsPagingAdapter : PagingDataAdapter<Cats, CatViewHolder>(AdapterDiffCallback()) {

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        if (cat != null) {
            holder.onBind(cat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder =
        CatViewHolder.create(parent)
}