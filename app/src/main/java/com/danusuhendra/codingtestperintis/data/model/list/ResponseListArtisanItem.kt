package com.danusuhendra.codingtestperintis.data.model.list


import com.google.gson.annotations.SerializedName

data class ResponseListArtisanItem(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("services")
    val services: List<Service>?,
    @SerializedName("user_image")
    val userImage: String?
)