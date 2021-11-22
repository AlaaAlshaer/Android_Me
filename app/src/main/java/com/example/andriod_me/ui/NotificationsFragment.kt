package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andriod_me.R
import com.example.andriod_me.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private var clicks = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        requireActivity().apply {
            title = getString(R.string.title_notifications)
        }

        if (savedInstanceState != null) {
            clicks = savedInstanceState.getInt(STATE_CLICKS)
            Log.d("SampleFragment", "savedInstanceState != NULL")
        } else {
            Log.d("SampleFragment", "savedInstanceState == NULL")
        }

        binding.button.setOnClickListener {
            clicks++
            binding.countText.text = clicks.toString()
        }

        // Initialize our text
        binding.countText.text = clicks.toString()

        return binding.root
    }

    companion object {
        val TAG: String = NotificationsFragment::class.java.simpleName
        fun newInstance() = NotificationsFragment()
        private const val STATE_CLICKS = "clicks"
    }


    init {
        Log.d(TAG, "NotificationsFragment")
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_CLICKS, clicks)
    }

}