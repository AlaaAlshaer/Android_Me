package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.andriod_me.R


class BodyPartFragment : Fragment() {


    private val TAG = this::class.simpleName
    private var mListIndex: ArrayList<Int> = arrayListOf()
    private var mImageIds: Int = 0
    private val listIndex = "listIndex"
    private val listImageIds = "imageIds"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageview: ImageView = inflater.inflate(
            R.layout.fragment_body_part,
            container,
            false
        ) as ImageView

        mImageIds = savedInstanceState?.getInt(listImageIds) ?: mImageIds
        mListIndex =
            savedInstanceState?.getIntegerArrayList(listIndex) ?: mListIndex


        if (mImageIds <= mListIndex.size - 1)
            imageview.setImageResource(mListIndex[mImageIds])
        else
            Log.d(TAG, "this fragment has a null list of image ids")

        imageview.setOnClickListener {
            mImageIds++
            if (mImageIds > mListIndex.size - 1) {
                mImageIds = 0
            }

            if (mListIndex.isNotEmpty())
                imageview.setImageResource(mListIndex[mImageIds])
            else
                Log.d(TAG, "this fragment has a null list of image ids")
        }

        return imageview
    }

    fun setMListIndex(mListIndex: ArrayList<Int>) {
        this.mListIndex = mListIndex
    }

    fun setMImageIds(mImageIds: Int) {
        this.mImageIds = mImageIds
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(listIndex, mListIndex)
        outState.putInt(listImageIds, mImageIds)
    }


}