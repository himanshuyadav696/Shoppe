package com.example.template.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvItemMoreBinding
import com.example.template.databinding.RcvNestedSportBinding
import com.example.template.models.get_home_page_response.TopVenue

class InnerSportListAdapter(
    private val context: Context?,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var resultList: List<TopVenue> = emptyList()
    private val VISIBLE_LIMIT = 3
    private val TYPE_ITEM = 0
    private val TYPE_MORE = 1

    // ---------------- ViewHolders ---------------- //

    inner class ItemViewHolder(private val binding: RcvNestedSportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TopVenue) {
            binding.apply {
                // Example:
                // tvGroundName.text = data.title
                // ivActivityImage.setImageResource(data.imageResId)
            }

        }
    }

    inner class MoreViewHolder(private val binding: RcvItemMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(remainingCount: Int) {
            binding.tvMore.text = "+$remainingCount More"
        }
    }

    // ---------------- Adapter Methods ---------------- //

    override fun getItemCount(): Int {
        return if (resultList.size > VISIBLE_LIMIT) VISIBLE_LIMIT + 1 else resultList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == VISIBLE_LIMIT && resultList.size > VISIBLE_LIMIT) TYPE_MORE else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_ITEM) {
            val binding = RcvNestedSportBinding.inflate(inflater, parent, false)
            ItemViewHolder(binding)
        } else {
            val binding = RcvItemMoreBinding.inflate(inflater, parent, false)
            MoreViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(resultList[position])
        } else if (holder is MoreViewHolder) {
            val remaining = resultList.size - VISIBLE_LIMIT
            holder.bind(remaining)
        }
    }

    // ---------------- Public Setter ---------------- //

    fun setData(data: List<TopVenue>) {
        resultList = data
        notifyDataSetChanged()
    }

}
