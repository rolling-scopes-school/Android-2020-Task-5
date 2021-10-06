package ng_designs.android_2021_task_5.data.cats.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)


data class GetAllImagesRequest(
    @Json(name="size") val size:String,
    @Json(name="mime_types") val mime_types: List<String>,
    @Json(name="order") val order: String,
    @Json(name="limit") val limit: Int,
    @Json(name="page") val page: Int,
    @Json(name="category_ids") val category_ids: List<Int>,
    @Json(name="format") val format: String,
    @Json(name="breed_id") val breed_id: String
)
