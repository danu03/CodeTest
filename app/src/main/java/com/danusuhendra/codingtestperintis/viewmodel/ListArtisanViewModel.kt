package com.danusuhendra.codingtestperintis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisanItem
import com.danusuhendra.codingtestperintis.repository.ListArtisanRepository

class ListArtisanViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListArtisanRepository()
    private var list = MutableLiveData<List<ResponseListArtisanItem>>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun listArtisan() {
        loading.value = true
        repository.listArtisan({
            list.value = it
            loading.value = false
        }, {
            error.value = it.message
            loading.value = false
        })
    }

    fun getListArtisan() : LiveData<List<ResponseListArtisanItem>> {
        return list
    }
}