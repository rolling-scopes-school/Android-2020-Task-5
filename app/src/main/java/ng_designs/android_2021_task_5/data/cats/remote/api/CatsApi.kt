package ng_designs.android_2021_task_5.data.cats.remote.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ng_designs.android_2021_task_5.data.cats.remote.dto.CatApiResponse
import ng_designs.android_2021_task_5.domain.common.utils.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

val logging =
    HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

val client =
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface CatsApi {
    @Headers("x-api-key: $API_KEY")
    @GET("images/search?")
    suspend fun getImagesFull(
        @Query("order") order: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<List<CatApiResponse>>

    @Headers("x-api-key: $API_KEY")
    @GET("images/search?size=thumb")
    suspend fun getThumbs(
        @Query("order") order: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<List<CatApiResponse>>
}

object CatsApiImpl {
    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

    private val catsApiService = retrofit.create(CatsApi::class.java)

    suspend fun getImageThumbs(
        order: String,
        limit: Int,
        page: Int
    ): Response<List<CatApiResponse>> {
        return withContext(Dispatchers.IO) {
            catsApiService.getImagesFull(order, limit, page)
        }
    }
}
