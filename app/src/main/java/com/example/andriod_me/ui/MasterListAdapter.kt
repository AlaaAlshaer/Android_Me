package com.example.andriod_me.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MasterListAdapter(
    private val context: Context,
    private val bodyPartsList: List<Int>,
) : BaseAdapter() {


    override fun getCount(): Int {
        return bodyPartsList.size
    }

    override fun getItem(p0: Int): Any {
        return bodyPartsList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return bodyPartsList[p0].toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val imageView: ImageView
        if (p1 != null)
            imageView = p1 as ImageView
        else {
            imageView = ImageView(context)
            imageView.setPadding(8, 8, 8, 8)
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        imageView.setImageResource(bodyPartsList[p0])
        return imageView
    }
}