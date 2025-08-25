package com.example.template.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvNewTemsBinding
import com.example.template.databinding.RcvRecentlyViewedBinding
import com.example.template.models.get_home_page_response.Sport

class RcvNewItemAdapter(
    private val context: Context?,
    private val onItemClick: () -> Unit,
    private var resultList: MutableList<Sport>?
) :
    RecyclerView.Adapter<RcvNewItemAdapter.ViewHolder>() {
    //  var resultList: List<Sport>? = null

    inner class ViewHolder(var binding: RcvNewTemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sport, position: Int) {
            Log.e("TAG", "bind:dd $data", )
            binding.apply {

            }
        }
    }


    override fun getItemCount(): Int {
        if (resultList == null) {
            return 0
        } else {
            return resultList?.size!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvNewTemsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: MutableList<Sport>?) {
        resultList = it
        notifyDataSetChanged()
    }

}