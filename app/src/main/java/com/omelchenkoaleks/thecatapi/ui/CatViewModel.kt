package com.omelchenkoaleks.thecatapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omelchenkoaleks.thecatapi.data.CatRepository

class CatViewModel : ViewModel() {
    private val repository = CatRepository()

    var cats = repository.letCatsLiveData().cachedIn(viewModelScope)
}
