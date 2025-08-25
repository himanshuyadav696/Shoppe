package com.example.template.ui.adapter

import IntroSlideData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.databinding.ItemIntroPageLayoutBinding

class IntroAdapter(
    private val introList: List<IntroSlideData>,
    private var context: Context
) :
    RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemIntroPageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IntroSlideData) {
            binding.tvHello.text = item.title
            binding.tvDesc.text = item.description
            Glide.with(context)
                .load(item.imageResId)
                .into(binding.ivImage)
           // binding.imageIntro.setImageResource(item.imageResId)
            if(position==introList.size-1){
                binding.btnDone.visibility = View.VISIBLE
            }else{
                binding.btnDone.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val binding = ItemIntroPageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introList[position])
    }

    override fun getItemCount(): Int = introList.size
}
