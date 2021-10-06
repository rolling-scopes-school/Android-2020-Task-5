package ng_designs.android_2021_task_5.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import ng_designs.android_2021_task_5.data.cats.repository.CatsRepository
import ng_designs.android_2021_task_5.domain.common.utils.locateByLazy

class MainViewModel : ViewModel() {
    private val repository: CatsRepository by locateByLazy()

    val cats = repository.getListData(viewModelScope).asLiveDataFlow()

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)
}