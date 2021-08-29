package com.danusuhendra.codingtestperintis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danusuhendra.codingtestperintis.data.model.detail.ResponseDetail
import com.danusuhendra.codingtestperintis.data.model.detail.Service
import com.danusuhendra.codingtestperintis.repository.DetailRepository

class DetailViewModel : ViewModel() {
    private val repository = DetailRepository()
    private val detailArtisan = MutableLiveData<List<ResponseDetail>>()
    private val service = MutableLiveData<List<Service>>()
    val error = MutableLiveData<String>()
    val loading : MutableLiveData<Boolean> = MutableLiveData()

    fun detail(id: String) {
        loading.value = true
        repository.detail(id, { data ->
            detailArtisan.value = data
            data.forEach {
                service.value = it.services as List<Service>?
            }
            loading.value = false
        }, {
            error.value = it.message
            loading.value = false
        })
    }

    fun getDetail() : LiveData<List<ResponseDetail>> {
        return detailArtisan
    }

    fun getService() : LiveData<List<Service>> {
        return service
    }
}