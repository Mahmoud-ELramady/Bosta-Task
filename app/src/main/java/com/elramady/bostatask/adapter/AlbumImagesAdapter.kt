package com.elramady.bostatask.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.elramady.bostatask.AlbumDetailsActivity
import com.elramady.bostatask.ImageDisplayActivity
import com.elramady.bostatask.R
import com.elramady.bostatask.databinding.ItemImageAlbumBinding
import com.elramady.bostatask.models.AlbumImage
import com.elramady.bostatask.utils.ALBUM_MODEL
import com.elramady.bostatask.utils.IMAGE_URL


private val USER_AGENT = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"

class AlbumImagesAdapter(val context:Context):
    RecyclerView.Adapter<AlbumImagesAdapter.AlbumImagesViewHolder>() {

    var listAlbumsImages=ArrayList<AlbumImage>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumImagesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)

        val binding: ItemImageAlbumBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_image_album,parent,false)
        return AlbumImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumImagesViewHolder, position: Int) {
        val albumsImages=listAlbumsImages[position]
        holder.bind(albumsImages)
    }

    override fun getItemCount(): Int {
        return listAlbumsImages.size
    }

    fun setListAlbumImages(list:List<AlbumImage>){
        this.listAlbumsImages=list as ArrayList<AlbumImage>
        notifyDataSetChanged()
    }

    inner class AlbumImagesViewHolder(val binding: ItemImageAlbumBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(albumsImages: AlbumImage) {
            val options = RequestOptions()
                .placeholder(R.drawable.ic_search)
                .error(R.drawable.ic_launcher_background)
                .override(300,300)

            Glide.with(context).load(albumsImages.url+".png").apply(options).dontAnimate().into(binding.imageItemAlbum)

            binding.imageItemAlbum.setOnClickListener {
                val intent= Intent(context, ImageDisplayActivity::class.java)
                intent.putExtra(IMAGE_URL,albumsImages.url+".png")
                context.startActivity(intent)
            }




//
//            val glideUrl = GlideUrl(
//                albumsImages.url,
//                LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())

        }

    }
}