package com.rovenhook.rsshool2021_android_task_catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        Glide.with(this)
            .load("https://cdn2.thecatapi.com/images/8LxU2Gwmo.jpg").into(imageView);

        runBlocking {
            CatAPIImplementation.getAllCats()
        }
    }


}