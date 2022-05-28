package com.elramady.bostatask.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elramady.bostatask.models.AlbumImage
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.models.UserModel
import com.elramady.bostatask.repo.UserRepositary
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo:UserRepositary):ViewModel() {
    private  var _downloadUserModel= MutableLiveData<Response<UserModel>>()
    val downloadUserModel: LiveData<Response<UserModel>>
        get() = _downloadUserModel

    private  var _downloadUserAlbums= MutableLiveData<Response<List<AlbumsModel>>>()
    val downloadUserAlbums: LiveData<Response<List<AlbumsModel>>>
        get() = _downloadUserAlbums


    private  var _downloadAlbumPhotos= MutableLiveData<Response<List<AlbumImage>>>()
    val downloadAlbumPhotos: LiveData<Response<List<AlbumImage>>>
        get() = _downloadAlbumPhotos



    @SuppressLint("CheckResult")
    fun getUserData(userId:Int) {
        repo.getUselModel(userId).subscribe({
            _downloadUserModel.value=it
        },{

        })
    }


    @SuppressLint("CheckResult")
    fun getUserAlbum(userId:Int) {
        repo.getAlbumUser(userId).subscribe({
             _downloadUserAlbums.value=it
        },{

        })
    }


    @SuppressLint("CheckResult")
    fun getAlbumPhotos(albumId:Int) {
        repo.getPhotos(albumId).subscribe({
            _downloadAlbumPhotos.value=it
            Log.e("photos",it.body().toString())
        },{

        })
    }
}
