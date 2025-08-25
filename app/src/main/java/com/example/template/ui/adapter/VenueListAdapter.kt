package com.example.template.ui.adapter

import VenueData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.RcvVenueListBinding


class VenueListAdapter(
    private val context: Context?,
    private val listener:OnItemClickListener
) :
    RecyclerView.Adapter<VenueListAdapter.ViewHolder>() {
    var resultList: List<VenueData>? = null

    inner class ViewHolder(var binding: RcvVenueListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VenueData, position: Int) {

            Log.d("tag","Infdhjh $data")
            binding.apply {
                tvGroundName.text = data.name ?: data.venue_name
                tvLocationValue.text = data.location

                val imageUrl = data.image_urls

                if (!imageUrl.isNullOrBlank()) {
                    Glide.with(itemView.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.baller_image4)
                        .error(R.drawable.baller_image4)
                        .into(binding.ivGroundImage)
                } else {
                    // Load default banner manually
                    Glide.with(itemView.context)
                        .load(R.drawable.baller_image4)
                        .into(binding.ivGroundImage)
                }


//              binding.ivViewPager.setImageResource(item.imageResId)

                //ivActivityImage.setImageResource(data.imageResId)
                cardMain.setOnClickListener {
                    listener.onItemClick(data,position)
                }
            }

          /*  binding.rcvSports.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                val setAdapter =  InnerSportListAdapter(context)
                adapter = setAdapter
                resultList?.let { setAdapter.setData(it) }
            }*/
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
        val binding = RcvVenueListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<VenueData>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(data: VenueData,position: Int)
    }
}