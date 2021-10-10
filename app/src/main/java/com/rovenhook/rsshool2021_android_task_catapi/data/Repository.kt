package com.rovenhook.rsshool2021_android_task_catapi.data

class Repository {
    suspend fun getMoreCats(page: Int) = CatApiImplementation.getAllCats(page)
}
