package com.example.template.ui.adapter

import PaidServicesJson
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R

class ContributorsAdapter (
        private val items: List<PaidServicesJson>
    ) : RecyclerView.Adapter<ContributorsAdapter.NestedViewHolder>() {

        inner class NestedViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            @SuppressLint("SetTextI18n")
            fun bind(item: PaidServicesJson) {
                val textView = view.findViewById<TextView>(R.id.tvName)
                val tvPrice = view.findViewById<TextView>(R.id.tvPrices)
                textView.text = item.paid_service_name
                tvPrice.text = "₹"+item.price.toString()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contributer_container, parent, false)
            return NestedViewHolder(view)
        }

        override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
            holder.bind(items[position])

        }

        override fun getItemCount() = items.size

}