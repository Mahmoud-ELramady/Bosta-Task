package com.elramady.bostatask.api

import com.elramady.bostatask.models.AlbumImage
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.models.UserModel
import com.elramady.bostatask.utils.GET_ALBUMS
import com.elramady.bostatask.utils.GET_PHOTOS
import com.elramady.bostatask.utils.GET_USER
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserInterface {
    @GET("$GET_USER/{user_id}")
    fun getUser(
        @Path("user_id") idUser:Int
    ): Single<Response<UserModel>>


    @GET(GET_ALBUMS)
    fun getAlbum(
        @Query("userId") userId:Int
    ): Single<Response<List<AlbumsModel>>>


    @GET(GET_PHOTOS)
    fun getPhotos(
        @Query("albumId") albumId:Int
    ): Single<Response<List<AlbumImage>>>

}

