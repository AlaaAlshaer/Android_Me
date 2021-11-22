package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andriod_me.R
import com.example.andriod_me.databinding.FragmentBodyPartBinding


class BodyPartFragment : Fragment() {


    var mHeadList = listOf<Int>()
    var mBodyList = listOf<Int>()
    var mLegList = listOf<Int>()
    var mHeadIds: Int = 0
    var mBodyIds: Int = 0
    var mLegIds: Int = 0
    private lateinit var binding: FragmentBodyPartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBodyPartBinding.inflate(inflater, container, false)
        requireActivity().apply {
            title = getString(R.string.body_part_title)
        }
        val imageviewHead = binding.fragmentBodyPartIvHead
        val imageviewBody = binding.fragmentBodyPartIvBody
        val imageviewLeg = binding.fragmentBodyPartIvLegs


        savedInstanceState?.apply {
            mHeadIds = savedInstanceState.getInt(HEAD_INDEX)
            mBodyIds = savedInstanceState.getInt(BODY_INDEX)
            mLegIds = savedInstanceState.getInt(LEG_INDEX)
            mHeadList = savedInstanceState.getIntArray(HEAD_LIST)!!.asList()
            mBodyList = savedInstanceState.getIntArray(BODY_LIST)!!.asList()
            mLegList = savedInstanceState.getIntArray(LEG_LIST)!!.asList()
        }


        if (mHeadList.isNotEmpty()) {
            imageviewHead.setImageResource(mHeadList[mHeadIds])
        }
        if (mBodyList.isNotEmpty()) {
            imageviewBody.setImageResource(mBodyList[mBodyIds])
        }
        if (mLegList.isNotEmpty()) {
            imageviewLeg.setImageResource(mLegList[mLegIds])
        }


        imageviewHead.setOnClickListener {
            mHeadIds++
            if (mHeadIds > mHeadList.size - 1) {
                mHeadIds = 0
            }
            if (mHeadList.isNotEmpty()) {
                imageviewHead.setImageResource(mHeadList[mHeadIds])
            }
        }

        imageviewBody.setOnClickListener {
            mBodyIds++
            if (mBodyIds > mBodyList.size - 1) {
                mBodyIds = 0
            }
            if (mBodyList.isNotEmpty()) {
                imageviewBody.setImageResource(mBodyList[mBodyIds])
            }
        }

        imageviewLeg.setOnClickListener {
            mLegIds++
            if (mLegIds > mLegList.size - 1) {
                mLegIds = 0
            }
            if (mLegList.isNotEmpty()) {
                imageviewLeg.setImageResource(mLegList[mLegIds])
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(HEAD_INDEX, mHeadIds)
        outState.putInt(BODY_INDEX, mBodyIds)
        outState.putInt(LEG_INDEX, mLegIds)
        outState.putIntArray(HEAD_LIST, mHeadList.toIntArray())
        outState.putIntArray(BODY_LIST, mBodyList.toIntArray())
        outState.putIntArray(LEG_LIST, mLegList.toIntArray())
    }

    init {
        Log.d("BodyPartFragment", "BodyPartFragment")
    }

    companion object {
        const val TAG: String = "BodyPartFragment"
        const val HEAD_INDEX: String = "head"
        const val BODY_INDEX: String = "body"
        const val LEG_INDEX: String = "leg"
        const val HEAD_LIST: String = "headList"
        const val BODY_LIST: String = "bodyList"
        const val LEG_LIST: String = "legList"
    }


}
