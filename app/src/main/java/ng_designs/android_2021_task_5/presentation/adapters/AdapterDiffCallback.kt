package ng_designs.android_2021_task_5.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ng_designs.android_2021_task_5.domain.cat.models.Cats

class AdapterDiffCallback : DiffUtil.ItemCallback<Cats>() {
    override fun areItemsTheSame(oldItem: Cats, newItem: Cats): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cats, newItem: Cats): Boolean = oldItem == newItem
}