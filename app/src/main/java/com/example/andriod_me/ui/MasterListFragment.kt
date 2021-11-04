package com.example.andriod_me.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.andriod_me.R

class MasterListFragment : Fragment() {

    private var bodyPartsList: List<Int> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gridView: GridView = inflater.inflate(
            R.layout.fragment_master_list,
            container,
            false
        ) as GridView

        val masterListAdapter = MasterListAdapter(requireContext(), bodyPartsList)
        gridView.adapter = masterListAdapter
        gridView.numColumns = 3
        return gridView
    }

    fun setBodyPartsList(bodyPartsList: List<Int>) {
        this.bodyPartsList = bodyPartsList
    }
}