package com.rovenhook.rsshool2021_android_task_catapi.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class CatsViewModel : ViewModel() {
    private var page: Int = 0

    private val _items = MutableLiveData<List<CatsApiData>>()
    val items: LiveData<List<CatsApiData>> get() = _items

    init {
        getMoreCats()
    }

    fun getAllCats() = items

    fun getMoreCats() {
        viewModelScope.launch {
            try {
                _items.value = CatApiImplementation.getAllCats(page)
                page++
            } catch (e: SocketTimeoutException) {
                delay(3000)
                _items.value = CatApiImplementation.getAllCats(page)
                page++
            }
        }
    }
}