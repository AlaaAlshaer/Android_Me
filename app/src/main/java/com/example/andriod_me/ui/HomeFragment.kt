package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.activity.addCallback
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset

import com.example.andriod_me.databinding.FragmentHomeBinding


class HomeFragment(private val mBodyPartList: List<Int>) : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var isBodyPartFragmentVisible = false
    private var isTabletMode = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().apply {
            title = getString(R.string.title_home)
        }

        savedInstanceState?.let {
            isBodyPartFragmentVisible = it.getBoolean(IS_BTN_CLICKED)
        }

        val gridView = binding.fragmentMasterListGv
        isTabletMode = binding.fragmentHomeSw600dpLinearLayout != null

        lateinit var bodyPartFragment: BodyPartFragment

        childFragmentManager.findFragmentByTag(BodyPartFragment.TAG)?.run {
            bodyPartFragment = this as BodyPartFragment
        } ?: run {
            bodyPartFragment = BodyPartFragment()
            bodyPartFragment.mBodyList = AndroidImageAsset.bodyList
            bodyPartFragment.mHeadList = AndroidImageAsset.headList
            bodyPartFragment.mLegList = AndroidImageAsset.legList
        }


        if (isTabletMode) {

            val bodyPartFragmentContainerTabletLayout =
                binding.fragmentHomeBodyPartFragmentContainer!!

            savedInstanceState ?: addFragment(
                bodyPartFragment,
                bodyPartFragmentContainerTabletLayout.id,
            )

        } else {

            val bodyPartFragmentContainerPhoneLayout =
                binding.fragmentHomeBodyPartContainer!!

            val button = binding.fragmentMasterListBtnNext!!

            activity?.onBackPressedDispatcher
                ?.addCallback(viewLifecycleOwner, true, {
                    isBodyPartFragmentVisible = false

                    childFragmentManager.commit {
                        detach(bodyPartFragment)
                    }
                    hideBodyPartFragmentContainerPhoneLayout(
                        bodyPartFragmentContainerPhoneLayout,
                        button,
                        gridView
                    )
                })

            if (isBodyPartFragmentVisible)
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    gridView
                )

            button.setOnClickListener {
                isBodyPartFragmentVisible = true
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    gridView
                )

                addFragment(
                    bodyPartFragment,
                    bodyPartFragmentContainerPhoneLayout.id,
                )

                refreshFragment(bodyPartFragment)
            }
        }

        gridView.setOnItemClickListener { _, _, position, _ ->

            when (position / 12) {
                0 -> bodyPartFragment.mHeadIds = selectImageIndex(position)
                1 -> bodyPartFragment.mBodyIds = selectImageIndex(position)
                2 -> bodyPartFragment.mLegIds = selectImageIndex(position)
            }

            if (isTabletMode)
                refreshFragment(bodyPartFragment)
        }

        gridView.adapter = MasterListAdapter(requireContext(), mBodyPartList)


        return binding.root
    }

    private fun hideBodyPartFragmentContainerPhoneLayout(
        f: FragmentContainerView,
        button: Button,
        gridView: GridView
    ) {
        f.visibility = View.GONE
        button.visibility = View.VISIBLE
        gridView.visibility = View.VISIBLE
    }

    private fun showBodyPartFragmentContainerPhoneLayout(
        f: FragmentContainerView,
        button: Button,
        gridView: GridView
    ) {
        f.visibility = View.VISIBLE
        button.visibility = View.GONE
        gridView.visibility = View.GONE
    }

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        const val IS_BTN_CLICKED: String = "isClicked"
        fun newInstance(mBodyPartList: List<Int>) = HomeFragment(mBodyPartList)
    }

    init {
        Log.d(TAG, "HomeFragment")
    }

    private fun selectImageIndex(position: Int): Int {
        val index: Int = position / 12
        return (position) - (12 * index)
    }

    private fun refreshFragment(fragment: BodyPartFragment) {
        childFragmentManager.commit {
            detach(fragment)
        }
        childFragmentManager.commit {
            attach(fragment)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_BTN_CLICKED, isBodyPartFragmentVisible)
    }

    private fun addFragment(fragment: BodyPartFragment, containerId: Int) {

        childFragmentManager.commit {
            setReorderingAllowed(true)
            when {
                fragment.isAdded -> return@commit
                fragment.isDetached -> attach(fragment)
                else -> add(
                    containerId,
                    fragment,
                    BodyPartFragment.TAG
                )
            }
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }


}