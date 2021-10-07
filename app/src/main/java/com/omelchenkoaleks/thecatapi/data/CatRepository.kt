package com.omelchenkoaleks.thecatapi.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.omelchenkoaleks.thecatapi.api.CatApi
import com.omelchenkoaleks.thecatapi.api.CatApiImpl

class CatRepository(private val catApi: CatApi = CatApiImpl.catApiService) {

    private fun getDefaultPageConfig() : PagingConfig {
        return PagingConfig(pageSize = 1, enablePlaceholders = false)
    }

    fun letCatsLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<Cat>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatPagingSource(catApi) }
        ).liveData
    }
}
