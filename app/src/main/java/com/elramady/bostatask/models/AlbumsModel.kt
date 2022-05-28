package com.elramady.bostatask.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumsModel(
    val id: Int,
    val title: String,
    val userId: Int
):Serializable