package com.example.template.ui.adapter

import Slot
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R
import com.example.template.databinding.RcvSlotsBinding
import com.example.template.utils.AppUtils


class ViewSlotAdapter(
    private val context: Context?,
    private val listner:onSlotClickListner
) :
    RecyclerView.Adapter<ViewSlotAdapter.ViewHolder>() {
    var resultList: List<Slot>? = null

    inner class ViewHolder(var binding: RcvSlotsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Slot, position: Int) {
            binding.apply {
                val startDate = AppUtils.convertTo12HourFormat(data.start_time)
                val endDate = AppUtils.convertTo12HourFormat(data.end_time)
                binding.tvEndTime.text = "$startDate - $endDate"
                binding.tvPrice.text = data.price

                when (data.status?.lowercase()) {
                    "available" -> {
                        binding.root.setBackgroundResource(R.drawable.bg_slot_available) // green box
                    }
                    "hold" -> {
                        binding.root.setBackgroundResource(R.drawable.bg_slot_pending) // orange box
                    }
                    "completed", "complete" -> {
                        binding.root.setBackgroundResource(R.drawable.bg_slot_completed) // red box
                    }
                    else -> {
//                        binding.root.setBackgroundResource(R.drawable.bg_slot_default) // default color
                        binding.root.setBackgroundResource(R.drawable.bg_slot_available) // green box

                    }
                }

        }
            itemView.setOnClickListener {
                listner.slotClicked(data,position)
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
        val binding = RcvSlotsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<Slot>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface onSlotClickListner{
        fun slotClicked(data: Slot,position: Int)
    }

}