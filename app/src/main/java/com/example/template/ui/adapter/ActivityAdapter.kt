package com.example.template.ui.adapter

import IntroSlideData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.SportItemContainerBinding
import com.example.template.models.get_home_page_response.Sport


class ActivityAdapter(
    private val context: Context?,
    private val listner: onItemClickListner
) :
    RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    var resultList: List<Sport>? = null

    inner class ViewHolder(var binding: SportItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sport, position: Int) {
           binding.apply {
               tvTitle.text = data.sport_name

               val imageUrl = data.icon

               if (!imageUrl.isNullOrBlank()) {
                   Glide.with(itemView.context)
                       .load(imageUrl)
                       .placeholder(R.drawable.banner_user)
                       .error(R.drawable.banner_user)
                       .into(binding.footballItem)
               } else {
                   // Load default banner manually
                   Glide.with(itemView.context)
                       .load(R.drawable.banner_user)
                       .into(binding.footballItem)
               }




//               footballItem.setImageResource(data.imageResId)
           }
            itemView.setOnClickListener {
//                listner.onItemClicked(data,position)
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
        val binding = SportItemContainerBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<Sport>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface onItemClickListner {
        fun onItemClicked(city: IntroSlideData,position: Int)
    }


}