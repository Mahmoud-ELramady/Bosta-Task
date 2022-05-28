package com.elramady.bostatask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.elramady.bostatask.adapter.UserAlbumsAdapter
import com.elramady.bostatask.databinding.ActivityMainBinding
import com.elramady.bostatask.models.AlbumsModel
import com.elramady.bostatask.models.UserModel
import com.elramady.bostatask.utils.ALBUM_MODEL
import com.elramady.bostatask.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserAlbumsAdapter.ClickListnerAlbumItem {

    lateinit var binding:ActivityMainBinding
    private val userViewModel:UserViewModel by viewModels()
    lateinit var adapterAlbum:UserAlbumsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)


        userViewModel.getUserData(2)
        userViewModel.downloadUserModel.observe(this, Observer {
            if (it.isSuccessful){
                bindData(it.body()!!)

                Log.e("success",it.body()!!.name)
            }
        })



        userViewModel.getUserAlbum(2)

        setRV()



    }

    private fun bindData(it: UserModel) {
        binding.tvName.text=it.name
        binding.tvAddress.text=it.address.street+", "+it.address.city
    }

    private fun setRV() {
        var layoutManagerAlbum= LinearLayoutManager(this)
        adapterAlbum=UserAlbumsAdapter(this,this)
        val dividerItemDecoration = DividerItemDecoration(
            binding.rv.getContext(),
            layoutManagerAlbum.getOrientation()
        )
        binding.rv.apply {
            adapter=adapterAlbum
            layoutManager=layoutManagerAlbum
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)

        }


        userViewModel.downloadUserAlbums.observe(this, Observer {
            if(it.isSuccessful){
                adapterAlbum.setListAlbum(it.body()!!)
            }
        })


    }

    override fun onClickItemAlbum(albumModel: AlbumsModel) {
        val intent= Intent(this,AlbumDetailsActivity::class.java)
        intent.putExtra(ALBUM_MODEL,albumModel)
        startActivity(intent)
    }


}