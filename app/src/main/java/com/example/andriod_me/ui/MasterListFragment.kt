package com.example.andriod_me.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.andriod_me.R
import java.lang.ClassCastException
import java.util.ArrayList

interface OnImageClickListener {
    fun onSelectedImage(position: Int)
}

interface OnButtonClickListener {
    fun onClickButton()
}

class MasterListFragment(private val mMasterListAdapter: MasterListAdapter) : Fragment() {

    private lateinit var onImageClickListener: OnImageClickListener
    private lateinit var onButtonClickListener: OnButtonClickListener
    var mBtnState = false
    private val btnState = "btnState"
    private val bodyPartsList = "bodyPartsList"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onButtonClickListener = context as OnButtonClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement OnButtonClickListener"
            )
        }
        try {
            onImageClickListener = context as OnImageClickListener

        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement OnImageClickListener"
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewRoot = inflater.inflate(
            R.layout.fragment_master_list,
            container,
            false
        )

        mBtnState = savedInstanceState?.getBoolean(btnState) ?: mBtnState
        mMasterListAdapter.setBodyPartsList(savedInstanceState?.getIntegerArrayList(bodyPartsList))

        val gridView = viewRoot.findViewById<GridView>(R.id.fragment_master_list_gv)
        val button = viewRoot.findViewById<Button>(R.id.fragment_master_list_btn_next)

        if (mBtnState) {
            button.visibility = View.GONE
            gridView.numColumns = 2
        }

        gridView.adapter = mMasterListAdapter

        gridView.setOnItemClickListener { _, _, itemPosition, _ ->
            onImageClickListener.onSelectedImage(itemPosition)
        }
        button.setOnClickListener {
            onButtonClickListener.onClickButton()
        }

        return viewRoot
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(
            bodyPartsList,
            mMasterListAdapter.getBodyPartsList() as ArrayList<Int>
        )
        outState.putBoolean(btnState, mBtnState)
    }

}