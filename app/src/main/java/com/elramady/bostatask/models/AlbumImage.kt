package com.elramady.bostatask.models


import com.google.gson.annotations.SerializedName

data class AlbumImage(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)