package com.rovenhook.rsshool2021_android_task_catapi.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.listeners.OnTryCatchErrorListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException

class CatsViewModel() : ViewModel() {
    private var page: Int = 0

    private val _items = MutableLiveData<List<CatsApiData>>()
    val items: LiveData<List<CatsApiData>> get() = _items

    fun getAllCats() = items

    fun getMoreCats() {
        viewModelScope.launch {
            try {
                _items.value = CatApiImplementation.getAllCats(page)
                page++
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}