package com.danusuhendra.codingtestperintis.ui.home.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danusuhendra.codingtestperintis.adapter.ListArtisanAdapter
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.data.model.list.ResponseListArtisanItem
import com.danusuhendra.codingtestperintis.ui.detail.DetailActivity
import com.danusuhendra.codingtestperintis.viewmodel.ListArtisanViewModel
import kotlinx.android.synthetic.main.fragment_listartisan.*

class ListArtisanFragment : Fragment() {
    private val listArtisan: MutableList<ResponseListArtisanItem> = mutableListOf()
    private lateinit var viewModel: ListArtisanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listartisan, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.rvArtisan)

        viewModel = ViewModelProvider(
            this
        )[ListArtisanViewModel::class.java]

        viewModel.listArtisan()

        rv.layoutManager = LinearLayoutManager(context)
        rv.setHasFixedSize(true)
        val adapterList = ListArtisanAdapter(listArtisan)
        rv.adapter = adapterList

        adapterList.setOnItemClickCallback(object : ListArtisanAdapter.OnClickCallback {
            override fun onItemClicked(data: ResponseListArtisanItem) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", data.id)
                startActivity(intent)
            }

        })

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                adapterList.filter.filter(p0)
            }

        })

        viewModel.getListArtisan().observe(viewLifecycleOwner, {
            if (it != null) {
                listArtisan.clear()
                listArtisan.addAll(it)
                adapterList.notifyDataSetChanged()
            }
        })

        viewModel.error.observe(viewLifecycleOwner, {
            Log.d("TAG", "onError: $it")
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            pbListArtisan.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}