package com.danusuhendra.codingtestperintis.repository

import com.danusuhendra.codingtestperintis.data.api.ApiResource
import com.danusuhendra.codingtestperintis.data.model.detail.ResponseDetail
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailRepository {

    fun detail(
        id: String,
        onSuccess: (List<ResponseDetail>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.apiService().detailArtisan(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<ResponseDetail>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<ResponseDetail>) {
                    onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

                override fun onComplete() {

                }

            })
    }
}