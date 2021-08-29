package com.danusuhendra.codingtestperintis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisan
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisanItem
import java.util.*
import kotlin.collections.ArrayList

class ListArtisanAdapter(private val listArtisan: List<ResponseListArtisanItem>) : RecyclerView.Adapter<ListArtisanAdapter.ViewHolder>(), Filterable {
    var listArtisanFilter = ArrayList<ResponseListArtisanItem>()
    private lateinit var onClickCallback: OnClickCallback

    fun setOnItemClickCallback(onClickCallback: OnClickCallback) {
        this.onClickCallback = onClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_artisan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listArtisan[position]
        Glide.with(holder.itemView).load(list.avatar).into(holder.imageArtisan)
        holder.nameArtisan.text = list.name
        holder.descArtisan.text = list.description

        holder.itemView.setOnClickListener {
            onClickCallback.onItemClicked(listArtisan[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listArtisan.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageArtisan = itemView.findViewById<ImageView>(R.id.ivArtisan)
        var nameArtisan = itemView.findViewById<TextView>(R.id.tvNameArtisan)
        var descArtisan = itemView.findViewById<TextView>(R.id.tvDescartisan)
    }

    interface OnClickCallback {
        fun onItemClicked(data: ResponseListArtisanItem)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    listArtisanFilter = listArtisan as ArrayList<ResponseListArtisanItem>
                } else {
                    val resultList = ArrayList<ResponseListArtisanItem>()
                    for (row in listArtisan) {
                        if (row.name!!.lowercase(Locale.getDefault()).contains(p0.toString())) {
                            resultList.add(row)
                        }
                    }
                    listArtisanFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listArtisanFilter
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listArtisanFilter = p1?.values as ArrayList<ResponseListArtisanItem>
                notifyDataSetChanged()
            }

        }
    }
}