package com.rovenhook.rsshool2021_android_task_catapi.data

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("v1/images/search?api_key=4afbda73-4352-4070-aeca-1a0cc54958ff&limit=90&mime_types=jpg,png&order=DESC")
    suspend fun getAllCats(@Query("page") page: Int): List<CatsApiData>
}