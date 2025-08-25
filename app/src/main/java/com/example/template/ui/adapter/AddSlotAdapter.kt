package com.example.template.ui.adapter

import SlotItem
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvAddSlotsBinding

class AddSlotAdapter(
    private val onDeleteClick: (Int) -> Unit,
    private val onStartTimeClick: (Int) -> Unit,
    private val onEndTimeClick: (Int) -> Unit,
) : RecyclerView.Adapter<AddSlotAdapter.SlotViewHolder>() {

    private val items = mutableListOf<SlotItem>()

    fun setItems(newItems: List<SlotItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class SlotViewHolder(val binding: RcvAddSlotsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SlotItem, position: Int) = with(binding) {
            tvStartTime.text = item.start_time.ifBlank { "--:--" }
            tvEndTime.text = item.end_time.ifBlank { "--:--" }

            // 👇 Prevent recursive TextWatcher issues
            if (etPrice.tag != null) {
                etPrice.removeTextChangedListener(etPrice.tag as TextWatcher)
            }

            etPrice.setText(item.price)

            val watcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    item.price = s.toString()
                }
            }

            etPrice.addTextChangedListener(watcher)
            etPrice.tag = watcher

            tvStartTime.setOnClickListener {
                onStartTimeClick(position)
            }

            tvEndTime.setOnClickListener {
                onEndTimeClick(position)
            }

            ivDelete.setOnClickListener {
                onDeleteClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvAddSlotsBinding.inflate(inflater, parent, false)
        return SlotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}
