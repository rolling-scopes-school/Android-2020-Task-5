package com.rovenhook.rsshool2021_android_task_catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rovenhook.rsshool2021_android_task_catapi.adapters.CatsAdapter
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.databinding.ActivityMainBinding
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnTryCatchErrorListener
import com.rovenhook.rsshool2021_android_task_catapi.screens.CatsListFragment
import com.rovenhook.rsshool2021_android_task_catapi.viewmodels.CatsViewModel
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, CatsListFragment())
                .commit()
        }
    }
}