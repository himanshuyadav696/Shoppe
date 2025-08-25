package com.example.template.ui.adapter
import Amenities
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.RcvItemAmmenitiesBinding

class SelectedAmmenitiesAdapter(
    private val context: Context?,
    private val onRemoveClick: (Amenities) -> Unit
) :
    RecyclerView.Adapter<SelectedAmmenitiesAdapter.ViewHolder>() {
    var resultList: List<Amenities>? = null
    var from:String?=null

    inner class ViewHolder(var binding: RcvItemAmmenitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Amenities) {
            binding.apply {
                itemText.text = data.name
                Glide.with(context!!)
                    .load("https://floater.brancosoft.co.in/public/icons/amenities.png")
                    .placeholder(R.drawable.ic_dropplet)
                    .into(iconStart)
                if(from=="view"){
                    iconClose.visibility = View.GONE
                }else{
                    iconClose.visibility = View.VISIBLE
                }
                iconClose.setOnClickListener {
                    onRemoveClick(data)
                }
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
        val binding = RcvItemAmmenitiesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position])
    }

    fun setData(it: List<Amenities>) {
        resultList = it
        notifyDataSetChanged()
    }
}