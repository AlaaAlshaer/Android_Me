package com.example.andriod_me.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andriod_me.R
import com.example.andriod_me.data.Image

class ImageListAdapter(private val imageArray: ArrayList<Image>) :
    RecyclerView.Adapter<ImageListAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageIv: ImageView = itemView.findViewById(R.id.image_rv_item_iv)
        val titleTv: TextView = itemView.findViewById(R.id.image_rv_item_tv_image_title)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_rv_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.imageIv.setImageResource(imageArray[position].resource)
        holder.titleTv.text = imageArray[position].title
    }

    override fun getItemCount(): Int {
        return imageArray.size
    }

}