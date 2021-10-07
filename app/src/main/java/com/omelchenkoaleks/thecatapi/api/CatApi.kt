package com.omelchenkoaleks.thecatapi.api

import com.omelchenkoaleks.thecatapi.data.Cat
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("/v1/images/search")
    suspend fun getCats(@Query("limit") size: Int, @Query("page") page: Int): List<Cat>

}

object CatApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    val catApiService: CatApi = retrofit.create(CatApi::class.java)
}
