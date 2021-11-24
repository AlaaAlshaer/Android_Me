package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.andriod_me.R
import com.example.andriod_me.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.fade)
        exitTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide_right)
        requireActivity().apply {
            title = getString(R.string.title_profile)
        }

        return binding.root
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }


    init {
        Log.d(TAG,"ProfileFragment")
    }


}