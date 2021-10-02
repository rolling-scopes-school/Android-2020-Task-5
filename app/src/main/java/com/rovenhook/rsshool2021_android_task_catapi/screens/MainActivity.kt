package com.rovenhook.rsshool2021_android_task_catapi.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rovenhook.rsshool2021_android_task_catapi.adapters.CatsAdapter
import com.rovenhook.rsshool2021_android_task_catapi.adapters.OnReachEndListener
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.ActivityMainBinding
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import android.net.ConnectivityManager




class MainActivity : AppCompatActivity(), OnReachEndListener {

    private lateinit var binding: ActivityMainBinding
    private var catList: ArrayList<CatsApiData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: CatsViewModel by viewModels<CatsViewModel>()
        val adapter = CatsAdapter(this as OnReachEndListener, this)
        binding.recyclerViewCats.adapter = adapter
        binding.recyclerViewCats.layoutManager = GridLayoutManager(this, 2)

        viewModel.getAllCats().observe(this, {
            catList.addAll(it)
            adapter.submitList(catList)
        })

        // indicates reaching the bottom of the screen
//        binding.recyclerViewCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (!recyclerView.canScrollVertically(1)) {
//                    onReachEnd()
//                }
//            }
//        })
    }

    override fun onReachEnd() {
        val viewModel: CatsViewModel by viewModels<CatsViewModel>()
        viewModel.getMoreCats()
    }
}