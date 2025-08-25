package com.example.template.ui.adapter

import Faq
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R
import com.example.template.databinding.RcvItemFaqBinding

class FaqAdapter(
        private val context: Context?
    ) : RecyclerView.Adapter<FaqAdapter.ViewHolder>() {

        private var resultList: MutableList<Faq> = mutableListOf()
        private var expandedPosition: Int = -1

        inner class ViewHolder(val binding: RcvItemFaqBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(data: Faq, position: Int) {
                binding.tvQuestion.text = data.question
                binding.tvAnswer.text = data.answer

                val isExpanded = position == expandedPosition
                binding.tvAnswer.visibility = if (isExpanded) View.VISIBLE else View.GONE
                binding.viewLine.visibility = if (isExpanded) View.VISIBLE else View.GONE

                binding.ivToggle.setImageResource(
                    if (isExpanded) R.drawable.ic_minus_hollow else R.drawable.ic_plus_hollow
                )

                binding.ivToggle.setOnClickListener {
                    val prevExpanded = expandedPosition
                    if (isExpanded) {
                        expandedPosition = -1
                        notifyItemChanged(position)
                    } else {
                        expandedPosition = position
                        notifyItemChanged(prevExpanded)
                        notifyItemChanged(position)
                    }
                }
            }
        }

        override fun getItemCount(): Int = resultList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = RcvItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(resultList[position], position)
        }

        fun setData(list: List<Faq>) {
            resultList = list.toMutableList()
            expandedPosition = -1 // Reset expanded state on new data
            notifyDataSetChanged()
        }
    }
