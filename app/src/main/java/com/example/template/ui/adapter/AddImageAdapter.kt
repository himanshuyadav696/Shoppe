package com.example.template.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.template.R

class AddImageAdapter(
    private var items: List<Uri?>,
    private val onItemClick: (Uri?) -> Unit,
    private val onRemoveClick: (Uri?) -> Unit
) : RecyclerView.Adapter<AddImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rcv_add_images, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val uri = items[position]

        if (uri == null) {
            holder.ivImage.setImageResource(R.drawable.add_photo) // plus icon
            holder.ivImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            holder.ivDelete.visibility = View.GONE
        } else {
            holder.ivImage.setImageURI(uri)
            holder.ivImage.scaleType = ImageView.ScaleType.CENTER_CROP
            holder.ivDelete.visibility = View.VISIBLE
        }

        holder.ivImage.setOnClickListener {
            onItemClick(uri)
        }

        holder.ivDelete.setOnClickListener {
            onRemoveClick(uri)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(newList: List<Uri?>) {
        items = newList
        notifyDataSetChanged()
    }
}
