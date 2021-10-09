package com.rovenhook.rsshool2021_android_task_catapi.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rovenhook.rsshool2021_android_task_catapi.data.CatApiImplementation
import com.rovenhook.rsshool2021_android_task_catapi.data.CatsApiData
import com.rovenhook.rsshool2021_android_task_catapi.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class CatsViewModel : ViewModel() {
    private var page: Int = 0
    private val items = MutableLiveData<List<CatsApiData>>(listOf())

    fun getAllCats() = items

    fun getMoreCats(repository: Repository) {
        viewModelScope.launch() {
            try {
                var temp: List<CatsApiData> = listOf()
                while (temp.size <= 0) {
                    temp = repository.getMoreCats(page)
                    if (temp.size <= 0) {
                        delay(6000)
                    }
                }
                items.value = temp
                page++
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}







//class CatsViewModel : ViewModel() {
//    private var page: Int = 0
//
//    private val _items = MutableLiveData<List<CatsApiData>>()
//    val items: LiveData<List<CatsApiData>> get() = _items
//
//    fun getAllCats() = items
//
//    fun getMoreCats(repository: Repository) {
//        viewModelScope.launch() {
//            try {
//                var temp: List<CatsApiData> = listOf()
//                while (temp.size <= 0) {
//                    temp = repository.getMoreCats(page)
//                    if (temp.size <= 0) {
//                        delay(6000)
//                    }
//                }
//                _items.value = temp
//                page++
//            } catch (e: SocketTimeoutException) {
//                e.printStackTrace()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}