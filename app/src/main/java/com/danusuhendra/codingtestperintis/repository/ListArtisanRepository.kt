package com.danusuhendra.codingtestperintis.repository

import com.danusuhendra.codingtestperintis.data.api.ApiResource
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisan
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisanItem
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListArtisanRepository {
    fun listArtisan(
        onSuccess: (ResponseListArtisan) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.apiService().listArtisan()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ResponseListArtisan> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseListArtisan) {
                    onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }
}