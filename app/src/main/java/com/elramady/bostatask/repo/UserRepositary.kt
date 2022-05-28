package com.elramady.bostatask.repo

import com.elramady.bostatask.api.UserInterface
import com.elramady.bostatask.models.AlbumImage
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.models.UserModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import retrofit2.Response
import javax.inject.Inject

class UserRepositary @Inject constructor(private val api:UserInterface) {

    fun getUselModel(userId:Int): Single<Response<UserModel>> {
        return  api.getUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getAlbumUser(userId:Int): Single<Response<List<AlbumsModel>>> {
        return  api.getAlbum(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPhotos(albumId:Int): Single<Response<List<AlbumImage>>> {
        return  api.getPhotos(albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}