package com.example.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.databinding.ItemViewpagerImageLayoutBinding
import com.example.template.models.get_home_page_response.Banner


class BannerAdapter(
//    private val introList: List<IntroSlideData>
      private var introList: List<Banner>
) :
    RecyclerView.Adapter<BannerAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemViewpagerImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Banner) {

            val imageUrl = item.image_url

            if (!imageUrl.isNullOrBlank()) {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.banner_user)
                    .error(R.drawable.banner_user)
                    .into(binding.ivViewPager)
            } else {
                // Load default banner manually
                Glide.with(itemView.context)
                    .load(R.drawable.banner_user)
                    .into(binding.ivViewPager)
            }


//            binding.ivViewPager.setImageResource(item.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val binding = ItemViewpagerImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introList[position])
    }

    override fun getItemCount(): Int = introList.size

    fun setItems(newBanners: List<Banner>) {
        introList = newBanners
        notifyDataSetChanged()
    }

}
