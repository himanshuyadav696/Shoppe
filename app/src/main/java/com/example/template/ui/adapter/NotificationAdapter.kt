package com.example.template.ui.adapter

import IntroSlideData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.NotificationItemContainerBinding

class NotificationAdapter(
        private val context: Context?,
        private val listener:OnItemClickListener
    ) :
        RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
        var resultList: List<IntroSlideData>? = null

        inner class ViewHolder(var binding: NotificationItemContainerBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(data: IntroSlideData, position: Int) {

                Log.d("tag","Infdhjh $data")
                binding.apply {
                    notifyTitle.text = data.title
                    timeData.text = data.description

                   /* val imageUrl = data.image_urls

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
*/

                    itemView.setOnClickListener {
//                        listener.onItemClick(data,position)
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
            val binding = NotificationItemContainerBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(resultList!![position], position)
        }

        fun setData(it: List<IntroSlideData>) {
            resultList = it
            notifyDataSetChanged()
        }

        interface OnItemClickListener{
            fun onItemClick(data: IntroSlideData,position: Int)
        }

}