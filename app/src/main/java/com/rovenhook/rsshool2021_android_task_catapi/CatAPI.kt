package com.rovenhook.rsshool2021_android_task_catapi

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface CatAPI {
    @GET ("v1/images/search?limit=5&page=10&order=Desc")
    suspend fun getAllCats(): Call<JSONObject>
}