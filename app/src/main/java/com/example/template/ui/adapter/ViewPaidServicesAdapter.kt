package com.example.template.ui.adapter

import ServiceList
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvViewPaidServicesBinding

class ViewPaidServicesAdapter(
    private val context: Context?,
) :
    RecyclerView.Adapter<ViewPaidServicesAdapter.ViewHolder>() {
    var resultList: List<ServiceList>? = null

    inner class ViewHolder(var binding: RcvViewPaidServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ServiceList, position: Int) {
            binding.apply {
                tvServiceName.text = data.service_name
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
        val binding = RcvViewPaidServicesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<ServiceList>) {
        resultList = it
        notifyDataSetChanged()
    }

}