package com.example.template.ui.adapter

import Amenities
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.databinding.RcvAddAmmentiesBinding

class AddAmenitiesAdapter(
    private val context: Context?,
) :
    RecyclerView.Adapter<AddAmenitiesAdapter.ViewHolder>() {
    var resultList: List<Amenities>? = null

    inner class ViewHolder(var binding: RcvAddAmmentiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Amenities, position: Int) {
            binding.apply {
                tvAmenities.text = data.name
                val isLast = position == resultList!!.lastIndex

                Glide.with(context!!)
                    .load(data.icon)
                    .into(ivIcon)
                checkbox.isChecked = data.isSelected
                checkbox.setOnCheckedChangeListener(null) // Prevent unwanted callback on recycle
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    resultList?.get(position)?.isSelected = isChecked
                }

                val bottomPadding = if (isLast) 70 else 0 // Change 100 to whatever you need (in pixels)
                root.setPadding(
                    binding.root.paddingLeft,
                    root.paddingTop,
                    binding.root.paddingRight,
                    bottomPadding
                )
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
        val binding = RcvAddAmmentiesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<Amenities>) {
        resultList = it
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<Amenities> {
        return resultList?.filter { it.isSelected } ?: emptyList()
    }

}