package com.omelchenkoaleks.thecatapi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.omelchenkoaleks.thecatapi.MainActivity
import com.omelchenkoaleks.thecatapi.R
import com.omelchenkoaleks.thecatapi.databinding.FragmentDetailsBinding

class CatDetailFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var saveButton: FloatingActionButton? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(MainActivity.URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgSingleCat: ImageView = binding.imgSingleCat
        saveButton = binding.fabSave

        Glide.with(requireActivity())
            .load(url)
            .placeholder(R.drawable.default_cat)
            .into(imgSingleCat)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
