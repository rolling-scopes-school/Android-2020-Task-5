package com.omelchenkoaleks.thecatapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.omelchenkoaleks.thecatapi.ui.listener.ItemClickListener

class MainActivity : AppCompatActivity(), ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onItemClick(url: String?) {
        TODO("Not yet implemented")
    }
}
