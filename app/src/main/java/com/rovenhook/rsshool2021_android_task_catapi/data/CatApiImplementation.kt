package com.rovenhook.rsshool2021_android_task_catapi.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CatApiImplementation {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val catsAPIService = retrofit.create(CatApi::class.java)

    suspend fun getAllCats(page: Int): List<CatsApiData> {
        return withContext(Dispatchers.IO) {
            catsAPIService.getAllCats(page)
        }
    }
}
