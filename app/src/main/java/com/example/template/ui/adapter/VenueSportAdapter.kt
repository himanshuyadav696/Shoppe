package com.example.template.ui.adapter

import IntroSlideData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R
import com.example.template.databinding.RcvSelectSportsBinding


class VenueSportAdapter(
    private val context: Context?,
    private val listner:OnItemClickListner
) :
    RecyclerView.Adapter<VenueSportAdapter.ViewHolder>() {
    var resultList: List<IntroSlideData>? = null
    private var selectedPosition = -1

    inner class ViewHolder(var binding: RcvSelectSportsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: IntroSlideData, position: Int) {
            binding.apply {
                val isSelected = position == selectedPosition
                val backgroundColor = if (isSelected) {
                    ContextCompat.getColor(context!!, R.color.green_accent) // You define this color in `colors.xml`
                } else {
                    ContextCompat.getColor(context!!, R.color.grey_light)
                }
                cardMain.setCardBackgroundColor(backgroundColor)

                cardMain.setOnClickListener {
                    val oldPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(oldPosition)
                    notifyItemChanged(selectedPosition)
                    listner.onItemClick(data, position)
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
        val binding = RcvSelectSportsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<IntroSlideData>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface OnItemClickListner {
        fun onItemClick(data: IntroSlideData, position: Int)
    }
}