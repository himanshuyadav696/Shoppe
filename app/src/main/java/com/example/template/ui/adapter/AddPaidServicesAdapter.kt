package com.example.template.ui.adapter

import ServiceList
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.databinding.RcvItemAddPaidServicesBinding

class AddPaidServicesAdapter(
    private val context:Context,
    private val onEditClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit,
    private val onPlusClick: (ServiceList) -> Unit
):RecyclerView.Adapter<AddPaidServicesAdapter.ViewHolder>(){
    private val resultList = mutableListOf<ServiceList>()
    var from = ""
    inner class ViewHolder(var binding:RcvItemAddPaidServicesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: ServiceList, position: Int) {
            Log.e("TAG", "bind: $from", )
            binding.apply {
                val amount = data.price?.toDoubleOrNull() ?: 0.0
                binding.tvPrice.text = "₹%.2f".format(amount)
                tvTitle.text = data.service_name
                tvDescription.text = data.description

                if(from=="viewCourt"){
                    binding.ivEdit.visibility = View.GONE
                    binding.ivDelete.visibility = View.GONE
                    binding.ivPlusPink.visibility = View.GONE

                }

                if(from=="BookSlot"){
                    binding.ivEdit.visibility = View.GONE
                    binding.ivDelete.visibility = View.GONE
                    binding.ivPlusPink.visibility = View.VISIBLE
                }

                if(from==""){
                    binding.ivEdit.visibility = View.VISIBLE
                    binding.ivDelete.visibility = View.VISIBLE
                    binding.ivPlusPink.visibility = View.GONE
                }
            }
            binding.ivEdit.setOnClickListener {
                onEditClick(adapterPosition)
            }

            binding.ivDelete.setOnClickListener {
                onDeleteClick(adapterPosition)
            }

            binding.ivPlusPink.setOnClickListener {
                onPlusClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding = RcvItemAddPaidServicesBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position], position)
    }

    fun submitList(newList: List<ServiceList>) {
        resultList.clear()
        resultList.addAll(newList)
        notifyDataSetChanged()
    }

}