package com.example.andriod_me.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.example.andriod_me.R

class ImageListAdapter(
    private val list: List<Int>,
    private val onImageClickListener: OnImageClickListener
) :
    RecyclerView.Adapter<ImageListAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIv: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_rv_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.imageIv.setImageResource(list[position])
        holder.imageIv.setOnClickListener {
            onImageClickListener.onImageClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnImageClickListener {
        fun onImageClick(position: Int)
    }

}