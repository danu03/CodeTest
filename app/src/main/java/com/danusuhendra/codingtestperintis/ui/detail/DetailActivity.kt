package com.danusuhendra.codingtestperintis.ui.detail

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.adapter.ServicesAdapter
import com.danusuhendra.codingtestperintis.data.model.detail.Service
import com.danusuhendra.codingtestperintis.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private val listServices: MutableList<Service> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgUserArtisan = findViewById<ImageView>(R.id.ivUserArtisan)
        val nameArtisan = findViewById<TextView>(R.id.tvNameArtisanDetail)
        val ratingArtisan = findViewById<RatingBar>(R.id.rbArtisan)
        val descArtisan = findViewById<TextView>(R.id.tvDescArtisanDetail)
        val rvServices = findViewById<RecyclerView>(R.id.rvServices)

        title = "Detail"

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        val id = intent.getStringExtra("id")
        if (id != null) {
            viewModel.detail(id)
        }

        rvServices.layoutManager = GridLayoutManager(this, 2)
        rvServices.setHasFixedSize(true)
        val serviceAdapter = ServicesAdapter(listServices)
        rvServices.adapter = serviceAdapter

        viewModel.getDetail().observe(this, {
            it.forEach { data ->
                Glide.with(this).load(data.avatar).into(imgUserArtisan)
                nameArtisan.text = data.name
                ratingArtisan.rating = data.rating?.toFloat() ?: 0f
                descArtisan.text = data.description
            }
        })

        viewModel.getService().observe(this, {
            if (it != null) {
                listServices.clear()
                listServices.addAll(it)
                serviceAdapter.notifyDataSetChanged()
            }
        })
        
        viewModel.error.observe(this, {
            Log.d(TAG, "onCreate: $it")
        })

        viewModel.loading.observe(this, {
            pbDetail.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}