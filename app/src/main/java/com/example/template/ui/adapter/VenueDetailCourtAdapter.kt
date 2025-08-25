package com.example.template.ui.adapter

import CourtList
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.RcvCourtListBinding

class VenueDetailCourtAdapter(
    private val context: Context?,
    private val listner:OnItemClickListner
) :
    RecyclerView.Adapter<VenueDetailCourtAdapter.ViewHolder>() {
    var resultList: List<CourtList>? = null

    inner class ViewHolder(var binding: RcvCourtListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CourtList, position: Int) {
            binding.apply {
                tvGroundTitle.text = data.court_name
                tvSportType.text = data.sport_name
                Glide.with(context!!)
                    .load(data.icon)
                    .placeholder(R.drawable.baller_image4)
                    .into(cricketItem)
                //ivActivityImage.setImageResource(data.imageResId)
                itemContainer.setOnClickListener {
                    listner.onItemClick(data,position)
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
        val binding = RcvCourtListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<CourtList>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface OnItemClickListner{
        fun onItemClick(data: CourtList,position: Int)
    }
}