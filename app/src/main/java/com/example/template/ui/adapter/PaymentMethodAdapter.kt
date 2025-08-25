package com.example.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.template.R
import com.example.template.core.data.PaymentMethodResponse
import com.example.template.databinding.PaymentMethodItemContainerBinding

class PaymentMethodAdapter (
        var paymentList: MutableList<PaymentMethodResponse>
    ) : Adapter<PaymentMethodAdapter.ViewModel>() {
        inner class ViewModel(var binding: PaymentMethodItemContainerBinding) : ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodAdapter.ViewModel {
            val binding = PaymentMethodItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewModel(binding)
        }

        override fun onBindViewHolder(holder: PaymentMethodAdapter.ViewModel, position: Int) {
            val paymentMethod = paymentList[position]
            holder.binding.cardTv.text = paymentMethod.name
            Glide.with(holder.itemView.context).load(paymentMethod.iconResId).into(holder.binding.cardImg)

            val icon = if (paymentMethod.isSelected) R.drawable.selected_icon else R.drawable.unselected_rounded_btn

            Glide.with(holder.itemView.context).load(icon).into(holder.binding.cardSelectionImg)

            holder.binding.layout.setOnClickListener {
                // Add navigation or action here if needed }
                paymentList.forEachIndexed { index, item ->
                    item.isSelected = index == position
                }
                notifyDataSetChanged()

            }
        }
        fun getSelectedPaymentMethod(): PaymentMethodResponse? {
            return paymentList.find { it.isSelected }
        }
        override fun getItemCount(): Int {
            return paymentList.size
        }

}