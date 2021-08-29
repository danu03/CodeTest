package com.danusuhendra.codingtestperintis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.data.model.detail.ResponseDetail
import com.danusuhendra.codingtestperintis.data.model.detail.Service

class ServicesAdapter(private val listServices: List<Service>) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = listServices[position]
        holder.nameService.text = service.name
        holder.priceService.text = "Price  : " + service.price
        holder.captionService.text = "Caption : " + service.caption
    }

    override fun getItemCount(): Int = listServices.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameService = itemView.findViewById<TextView>(R.id.tvService)
        var priceService = itemView.findViewById<TextView>(R.id.tvPriceService)
        var captionService = itemView.findViewById<TextView>(R.id.tvCaptionService)
    }
}