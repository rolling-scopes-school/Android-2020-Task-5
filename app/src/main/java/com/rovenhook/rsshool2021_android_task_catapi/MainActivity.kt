package com.rovenhook.rsshool2021_android_task_catapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rovenhook.rsshool2021_android_task_catapi.databinding.ActivityMainBinding
import com.rovenhook.rsshool2021_android_task_catapi.screens.CatsListFragment

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
