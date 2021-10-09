package com.rovenhook.rsshool2021_android_task_catapi.data

import com.squareup.moshi.Json

data class CatsApiData(
    @Json(name = "id")val id: String,
    @Json(name = "url")val url: String
)
