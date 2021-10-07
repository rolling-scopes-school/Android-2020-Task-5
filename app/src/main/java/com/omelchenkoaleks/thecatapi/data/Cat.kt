package com.omelchenkoaleks.thecatapi.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    @Json(name = "id") val id: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "width") val width: String?,
    @Json(name = "height") val height: String?
)
