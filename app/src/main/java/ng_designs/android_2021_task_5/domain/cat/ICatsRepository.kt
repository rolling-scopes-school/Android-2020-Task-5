package ng_designs.android_2021_task_5.domain.cat

import android.widget.ListAdapter
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ng_designs.android_2021_task_5.data.cats.repository.CatsRepository
import ng_designs.android_2021_task_5.domain.cat.models.Cats
import ng_designs.android_2021_task_5.presentation.adapters.CatViewHolder

interface ICatsRepository {

    fun getListData(scope: CoroutineScope): Flow<PagingData<Cats>>

//    suspend fun saveCat(order: Cats)
//    suspend fun removeCat(order: Cats)
//    suspend fun updateCat(order: Cats)
//    suspend fun clearTable()
}