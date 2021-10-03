package com.rovenhook.rsshool2021_android_task_catapi.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.rovenhook.rsshool2021_android_task_catapi.R
import com.rovenhook.rsshool2021_android_task_catapi.adapters.CatsAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.FragmentCatsListBinding
import com.rovenhook.rsshool2021_android_task_catapi.databinding.FragmentDetailedViewBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnSmallImageClickListener
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel
import java.lang.Exception
import java.net.SocketTimeoutException

class DetailedViewFragment(private val imageView: ImageView) : Fragment() {
    private var _binding: FragmentDetailedViewBinding? = null
    private val binding: FragmentDetailedViewBinding
        get() = _binding ?: throw Exception("Binding error")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedViewBinding.inflate(inflater, container, false)

        binding.imageViewDetailed.load(imageView.drawable)

        binding.imageViewDetailed.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}