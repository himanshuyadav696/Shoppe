package com.example.template.ui.adapter

import ServiceList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvAddedPaidServicesBinding

class AddedPaidServiceAdapter(
    private val onPlus: (ServiceList) -> Unit,
    private val onMinus: (ServiceList) -> Unit,
    private val onDelete: (ServiceList) -> Unit
) : RecyclerView.Adapter<AddedPaidServiceAdapter.ViewHolder>() {
    private val addedList = mutableListOf<ServiceList>()

    inner class ViewHolder(val binding: RcvAddedPaidServicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ServiceList) {
            binding.tvServiceName.text = data.service_name
            binding.tvServiceDesc.text = data.description
//            val totalPrice = data.price?.toInt()?.times(data.quantity)
//            binding.tvPrice.text = totalPrice.toString()

            val totalPrice = data.price?.toDouble()?.times(data.quantity)?: 0
            binding.tvPrice.text = "₹%.2f".format(totalPrice)

            binding.tvQty.text = data.quantity.toString()

            binding.btnPlus.setOnClickListener {
                onPlus(data)
            }
            binding.btnMinus.setOnClickListener {
                onMinus(data)
            }
            binding.btnDelete.setOnClickListener{
                onDelete(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RcvAddedPaidServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = addedList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addedList[position])
    }

    fun submitList(newList: List<ServiceList>) {
        addedList.clear()
        addedList.addAll(newList)
        notifyDataSetChanged()
    }
}
