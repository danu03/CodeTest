package com.danusuhendra.codingtestperintis.data.model.list


import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?
)