package ng_designs.android_2021_task_5.data.cats.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import ng_designs.android_2021_task_5.data.cats.remote.api.CatsApiImpl
import ng_designs.android_2021_task_5.domain.cat.ICatsRepository
import ng_designs.android_2021_task_5.domain.cat.models.Cats
import ng_designs.android_2021_task_5.domain.common.utils.locateByLazy
import ng_designs.android_2021_task_5.presentation.adapters.CatsPagingSource

class CatsRepository() : ICatsRepository {
    private val api : CatsApiImpl by locateByLazy()

    override fun getListData(scope: CoroutineScope): Flow<PagingData<Cats>> {
        return runBlocking { Pager (config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = true),
            pagingSourceFactory = { CatsPagingSource(api) }).flow.cachedIn(scope)
        }
    }

//    override suspend fun saveCat(order: Cats) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun removeCat(order: Cats) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updateCat(order: Cats) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun clearTable() {
//        TODO("Not yet implemented")
//    }
}