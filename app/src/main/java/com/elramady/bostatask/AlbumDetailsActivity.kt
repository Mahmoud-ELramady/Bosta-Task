package com.elramady.bostatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elramady.bostatask.adapter.AlbumImagesAdapter
import com.elramady.bostatask.adapter.UserAlbumsAdapter
import com.elramady.bostatask.databinding.ActivityAlbumDetailsBinding
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.utils.ALBUM_MODEL
import com.elramady.bostatask.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsActivity : AppCompatActivity() {

    lateinit var binding:ActivityAlbumDetailsBinding
    lateinit var adapterImages: AlbumImagesAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_album_details)

        val modelAlbum = intent.getSerializableExtra(ALBUM_MODEL) as? AlbumsModel

        userViewModel.getAlbumPhotos(modelAlbum!!.id)

        binding.titleAlbum.text=modelAlbum.title



        setGrid()


    }

    private fun setGrid() {
        adapterImages=AlbumImagesAdapter(this)

        var layoutManagerImages= GridLayoutManager(this,3)

        binding.rvImages.apply {
            adapter=adapterImages
            layoutManager=layoutManagerImages
            setHasFixedSize(true)

        }

        userViewModel.downloadAlbumPhotos.observe(this, Observer {
            if (it.isSuccessful){
                adapterImages.setListAlbumImages(it.body()!!)

            }
        })





    }
}