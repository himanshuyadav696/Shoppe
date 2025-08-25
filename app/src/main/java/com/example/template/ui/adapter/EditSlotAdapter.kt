package com.example.template.ui.adapter

import SlotItem
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvViewSlotsBinding
import com.example.template.utils.AppUtils

class EditSlotAdapter(
    private val context: Context?,
    private val listner:OnItemClickListner
) :
    RecyclerView.Adapter<EditSlotAdapter.ViewHolder>() {
    var resultList: List<SlotItem>? = null

    inner class ViewHolder(var binding: RcvViewSlotsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SlotItem, position: Int) {
            binding.apply {
                tvSlotTitle.text = "Slot ${position + 1}"
                tvStartTime.text = AppUtils.convertTo12HourFormat(data.start_time)
                tvEndTime.text = AppUtils.convertTo12HourFormat(data.end_time)
                tvRent.text = data.price
                tvCancelSlot.setOnClickListener {
                    listner.onItemClick(data,position)
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
        val binding = RcvViewSlotsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList!![position], position)
    }

    fun setData(it: List<SlotItem>) {
        resultList = it
        notifyDataSetChanged()
    }

    interface OnItemClickListner{
        fun onItemClick(data: SlotItem,position: Int)
    }
}