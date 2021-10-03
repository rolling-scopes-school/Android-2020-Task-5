package com.rovenhook.rsshool2021_android_task_catapi.screens

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rovenhook.rsshool2021_android_task_catapi.R
import com.rovenhook.rsshool2021_android_task_catapi.adapters.CatsAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.FragmentCatsListBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnSmallImageClickListener
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel
import java.lang.Exception
import java.net.SocketTimeoutException

class CatsListFragment : Fragment(), OnSmallImageClickListener {
    private var _binding: FragmentCatsListBinding? = null
    private val binding: FragmentCatsListBinding
        get() = _binding ?: throw Exception("Binding error")
    private val viewModel: CatsViewModel by viewModels()
    private var catList: ArrayList<CatsApiData> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)

        val adapter = CatsAdapter(this as OnSmallImageClickListener)
        binding.recyclerViewCats.adapter = adapter
        val spanCount = when {
            this.getResources()
                .getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT -> 2
            else -> 3
        }
        binding.recyclerViewCats.layoutManager = GridLayoutManager(context, spanCount)

        viewModel.getAllCats().observe(viewLifecycleOwner, {
            catList.addAll(it)
            adapter.submitList(catList)
        })
        getCats()

        // indicates reaching the bottom of the screen
        binding.recyclerViewCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getMoreCats()
                }
            }
        })
        return binding.root
    }

    fun getCats() {
        viewModel.getMoreCats()
    }

    override fun onSmallImageClick(imageView: ImageView) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out)
            .replace(R.id.mainContainer, DetailedViewFragment(imageView))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}