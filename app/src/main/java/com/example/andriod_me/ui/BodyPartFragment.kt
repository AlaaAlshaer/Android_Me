package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.example.andriod_me.R
import com.example.andriod_me.databinding.FragmentBodyPartBinding
import com.example.andriod_me.viewModel.HomeFragmentViewModel


class BodyPartFragment : Fragment() {

    private lateinit var binding: FragmentBodyPartBinding

    private val viewModel: HomeFragmentViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBodyPartBinding.inflate(inflater, container, false)
        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.fade)
        exitTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide_right)
        requireActivity().apply {
            title = getString(R.string.body_part_title)
        }
        val imageviewHead = binding.fragmentBodyPartIvHead
        val imageviewBody = binding.fragmentBodyPartIvBody
        val imageviewLeg = binding.fragmentBodyPartIvLegs



        viewModel.headList.observe(viewLifecycleOwner,{headList ->
            var index = 0
            imageviewHead.setImageResource(headList[index])

            viewModel.selectedHead.observe(viewLifecycleOwner, {
                index = it
                imageviewHead.setImageResource(headList[it])
            })

            imageviewHead.setOnClickListener {
                index++
                if (index > headList.size - 1) {
                    index = 0
                }
                viewModel.selectHead(index)
            }
        })

        viewModel.bodyList.observe(viewLifecycleOwner,{bodyList ->
            var index = 0
            imageviewBody.setImageResource(bodyList[index])

            viewModel.selectedBody.observe(viewLifecycleOwner, {
                index = it
                imageviewBody.setImageResource(bodyList[it])
            })

            imageviewBody.setOnClickListener {
                index++
                if (index > bodyList.size - 1) {
                    index = 0
                }
                viewModel.selectBody(index)
            }
        })

        viewModel.legList.observe(viewLifecycleOwner,{legList ->
            var index = 0
            imageviewLeg.setImageResource(legList[index])

            viewModel.selectedLeg.observe(viewLifecycleOwner, {
                index = it
                imageviewLeg.setImageResource(legList[it])
            })

            imageviewLeg.setOnClickListener {
                index++
                if (index > legList.size - 1) {
                    index = 0
                }
                viewModel.selectLeg(index)
            }
        })

        return binding.root
    }

    init {
        Log.d(TAG, "init")
    }

    companion object {
        const val TAG: String = "BodyPartFragment"
    }


}
