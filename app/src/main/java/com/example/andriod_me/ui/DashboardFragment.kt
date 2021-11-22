package com.example.andriod_me.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andriod_me.R
import com.example.andriod_me.data.Image

import com.example.andriod_me.databinding.FragmentDashboardBinding


class DashboardFragment(private val mImageArrayList: ArrayList<Image>) : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        requireActivity().apply {
            title = getString(R.string.title_dashboard)
        }

        val rv = binding.fragmentImageListRv
        rv.adapter = ImageListAdapter(mImageArrayList)

        return binding.root
    }


    companion object {
        val TAG: String = DashboardFragment::class.java.simpleName
        fun newInstance(mImageArrayList: ArrayList<Image>) = DashboardFragment(mImageArrayList)
    }

    init {
        Log.d(TAG, "DashboardFragment")
    }


}