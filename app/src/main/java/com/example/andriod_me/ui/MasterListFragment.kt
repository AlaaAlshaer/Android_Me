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
import com.example.andriod_me.data.AndroidImageAsset
import java.lang.ClassCastException

interface OnImageClickListener {
    fun onSelectedImage(position: Int)
}

interface OnButtonClickListener {
    fun onClickButton()
}

class MasterListFragment : Fragment() {

    private lateinit var onImageClickListener: OnImageClickListener
    private lateinit var onButtonClickListener: OnButtonClickListener

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

        val gridView = viewRoot.findViewById<GridView>(R.id.fragment_master_list_gv)
        val button = viewRoot.findViewById<Button>(R.id.fragment_master_list_btn_next)
        val masterListAdapter = MasterListAdapter(requireContext(), AndroidImageAsset.allPart)
        gridView.adapter = masterListAdapter
        gridView.numColumns = 3


        gridView.setOnItemClickListener { _, _, itemPosition, _ ->
            onImageClickListener.onSelectedImage(itemPosition)
        }
        button.setOnClickListener {
            onButtonClickListener.onClickButton()
        }

        return viewRoot
    }

}