package com.example.template.ui.adapter

import PaidServicesJson
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R

class CheckOutPagePaidSevAdapter(
        private val items: List<PaidServicesJson>
    ) : RecyclerView.Adapter<CheckOutPagePaidSevAdapter.NestedViewHolder>() {

        inner class NestedViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(item: PaidServicesJson) {
                val textView = view.findViewById<TextView>(R.id.tvBottle)
                val textViewPrice = view.findViewById<TextView>(R.id.tvBottlePrValue)
                textView.text = item.paid_service_name
                textViewPrice.text = "₹"+item.price.toString()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_page_paid_sev_list, parent, false)
            return NestedViewHolder(view)
        }

        override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
            holder.bind(items[position])

        }

        override fun getItemCount() = items.size

}