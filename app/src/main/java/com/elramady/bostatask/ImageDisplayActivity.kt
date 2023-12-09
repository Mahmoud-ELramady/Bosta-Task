package com.elramady.bostatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elramady.bostatask.databinding.ActivityImageDisplayBinding
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.utils.ALBUM_MODEL
import com.elramady.bostatask.utils.IMAGE_URL

class ImageDisplayActivity : AppCompatActivity() {
    lateinit var binding:ActivityImageDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_image_display)
        val imageUrl = intent.getStringExtra(IMAGE_URL)

        val options = RequestOptions()
            .placeholder(R.drawable.ic_search)
            .error(R.drawable.ic_launcher_background)
            .override(300,300)

        Glide.with(this).load("$imageUrl.png").apply(options).dontAnimate().into(binding.imageViewAlbum)



    }
}