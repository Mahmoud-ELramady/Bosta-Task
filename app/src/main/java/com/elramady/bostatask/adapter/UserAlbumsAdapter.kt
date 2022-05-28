package com.elramady.bostatask.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elramady.bostatask.R
import com.elramady.bostatask.databinding.ItemAlbumBinding
import com.elramady.bostatask.models.AlbumsModel

class UserAlbumsAdapter(val context:Context,val listner: ClickListnerAlbumItem): RecyclerView.Adapter<UserAlbumsAdapter.UserAlbumsViewHolder>() {

    var listAlbums=ArrayList<AlbumsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumsViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)

        val binding:ItemAlbumBinding=
            DataBindingUtil.inflate(layoutInflater, R.layout.item_album,parent,false)

        return UserAlbumsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAlbumsViewHolder, position: Int) {
        val albums=listAlbums[position]
        holder.bind(albums)
    }

    override fun getItemCount(): Int {
       return listAlbums.size
    }
    fun setListAlbum(list:List<AlbumsModel>){
        this.listAlbums=list as ArrayList<AlbumsModel>
        notifyDataSetChanged()
    }

    inner class UserAlbumsViewHolder(val binding:ItemAlbumBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(albums: AlbumsModel) {
            binding.tvItemAlbumName.text=albums.title

            binding.tvItemAlbumName.setOnClickListener {
                listner.onClickItemAlbum(albums)
            }

        }

    }

    interface ClickListnerAlbumItem{
        fun onClickItemAlbum(albumModel: AlbumsModel)

    }


}