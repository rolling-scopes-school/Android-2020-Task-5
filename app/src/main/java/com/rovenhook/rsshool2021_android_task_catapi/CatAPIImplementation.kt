package com.rovenhook.rsshool2021_android_task_catapi

import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object CatAPIImplementation {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val catsAPIService = retrofit.create(CatAPI::class.java)

    suspend fun getAllCats() {
//        val s = catsAPIService.getAllCats().execute().body()?.toString() ?: "nothing"
//        Log.i("log-tag", s)

        // https://www.youtube.com/watch?v=7qI-W6qI8T4
    }
}