package com.danusuhendra.codingtestperintis.data.api

import com.danusuhendra.codingtestperintis.data.model.detail.ResponseDetail
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisan
import com.danusuhendra.codingtestperintis.utils.DETAILS
import com.danusuhendra.codingtestperintis.utils.LIST_ARTISAN
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET(LIST_ARTISAN)
    fun listArtisan() : Observable<ResponseListArtisan>

    @GET(DETAILS)
    fun detailArtisan(
        @Query("id") id: String
    ) : Observable<List<ResponseDetail>>
}