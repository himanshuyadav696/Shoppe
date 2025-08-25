package com.example.template.ui.adapter

import IntroSlideData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.ItemViewpagerImageLayoutBinding


class ViewDetailBannerAdapter(private val introList: List<IntroSlideData>) :
    RecyclerView.Adapter<ViewDetailBannerAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemViewpagerImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IntroSlideData) {
            binding.ivViewPager.setImageResource(item.imageResId)
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
}