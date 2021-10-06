package ng_designs.android_2021_task_5.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ng_designs.android_2021_task_5.R
import ng_designs.android_2021_task_5.databinding.ImageItemContainerBinding
import ng_designs.android_2021_task_5.domain.cat.models.Cats

class CatViewHolder (private val binding: ImageItemContainerBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Cats) {

        binding.imageView.load(item.url){
            placeholder(R.drawable.placeholder_animation)
        }

        itemView.setOnClickListener {
            val bundle = bundleOf("imageUrl" to item.url)
            binding.imageView.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }
    }

//    private fun <T> views(block: ImageItemContainerBinding.() -> T): T? = binding.block()

    companion object {
        fun create(parent: ViewGroup) = ImageItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let(::CatViewHolder)
    }
}

