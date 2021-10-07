package com.omelchenkoaleks.thecatapi.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omelchenkoaleks.thecatapi.databinding.FragmentCatGalleryBinding
import com.omelchenkoaleks.thecatapi.ui.adapter.CatAdapter
import com.omelchenkoaleks.thecatapi.ui.listener.ItemClickListener
import kotlinx.coroutines.launch

class FragmentCatGallery : Fragment() {

    private val viewModel by viewModels<CatViewModel>()

    private var _binding: FragmentCatGalleryBinding? = null
    private val binding get() = _binding!!

    private var itemClickListener: ItemClickListener? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: CatAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        itemClickListener = context as ItemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatGalleryBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = CatAdapter(itemClickListener)
        recyclerView?.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cats.observe(viewLifecycleOwner, { cats ->
            cats?.let {
                lifecycleScope.launch {
                    adapter?.submitData(it)
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
