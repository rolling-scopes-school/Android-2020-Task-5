package com.rovenhook.rsshool2021_android_task_catapi.screens

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rovenhook.rsshool2021_android_task_catapi.R
import com.rovenhook.rsshool2021_android_task_catapi.adapters.CatsAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.data.Repository
import com.rovenhook.rsshool2021_android_task_catapi.databinding.FragmentCatsListBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnSmallImageClickListener
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel

class CatsListFragment : Fragment(), OnSmallImageClickListener {
    private var _binding: FragmentCatsListBinding? = null
    private val binding: FragmentCatsListBinding
        get() = _binding ?: throw Exception("Binding error")
    private val viewModel: CatsViewModel by viewModels()
    private var catList: ArrayList<CatsApiData> = arrayListOf()
    private val repository: Repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CatsAdapter(
            this as OnSmallImageClickListener
        )
        binding.recyclerViewCats.adapter = adapter
        val spanCount = when {
            this.getResources()
                .getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT -> 2
            else -> 3
        }
        binding.recyclerViewCats.layoutManager = GridLayoutManager(context, spanCount)
        viewModel.getMoreCats(repository)

        viewModel.getAllCats().observe(viewLifecycleOwner, {
            if (it.size > 0) {
//                adapter.currentList.addAll(it)
                catList.addAll(it)
                adapter.submitList(catList.toList())
                binding.progressBar.isVisible = false
            }
        })

        // indicates reaching the bottom of the screen
        binding.recyclerViewCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    binding.progressBar.isVisible = true
                    viewModel.getMoreCats(repository)
                }
            }
        })
    }

    override fun onSmallImageClick(imageView: ImageView) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )
            .replace(R.id.mainContainer, DetailedViewFragment(imageView))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
