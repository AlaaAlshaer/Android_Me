package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset

import com.example.andriod_me.databinding.FragmentHomeBinding


class HomeFragment(private val mBodyPartList: List<Int>) : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var mIndexHead = 0
    private var mIndexBody = 0
    private var mIndexLeg = 0

    private var isNextButtonClicked = false
    private var isSw600dpLayout = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().apply {
            title = getString(R.string.title_home)
        }


        val button = binding.fragmentMasterListBtnNext
        val gridView = binding.fragmentMasterListGv

        //may be null
        val bodyPartFragmentContainerPhoneLayout = binding.fragmentHomeBodyPartContainer

        binding.fragmentHomeLinearLayout?.let {
            isSw600dpLayout = true
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!isSw600dpLayout) {
                        isNextButtonClicked = false
                        hideBodyPartFragmentContainerPhoneLayout(
                            bodyPartFragmentContainerPhoneLayout!!,
                            button!!,
                            gridView
                        )
                        Log.d(TAG, "OnBackPressed")
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })

        lateinit var bodyPartFragment: BodyPartFragment

        binding.fragmentHomeLinearLayout?.let {

            button?.visibility = View.GONE

            val bodyPartFragmentContainerTabletLayout =
                binding.fragmentHomeBodyPartFragmentContainer!!




            childFragmentManager.findFragmentByTag(BodyPartFragment.TAG)?.run {
                bodyPartFragment = this as BodyPartFragment
            } ?: run {
                bodyPartFragment = BodyPartFragment()
                bodyPartFragment.mBodyList = AndroidImageAsset.bodyList
                bodyPartFragment.mHeadList = AndroidImageAsset.headList
                bodyPartFragment.mLegList = AndroidImageAsset.legList
            }


            savedInstanceState?.apply {
                Log.d(TAG, "savedInstanceState != NULL")

            } ?: apply {
                Log.d(TAG, "savedInstanceState == NULL")
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    if (!bodyPartFragment.isAdded)
                        add(
                            bodyPartFragmentContainerTabletLayout.id,
                            bodyPartFragment,
                            BodyPartFragment.TAG
                        )
                }
            }


            gridView.setOnItemClickListener { _, _, position, _ ->
                when (position / 12) {
                    0 -> {
                        bodyPartFragment.mHeadIds = selectImageIndex(position)
                    }
                    1 -> {
                        bodyPartFragment.mBodyIds = selectImageIndex(position)
                    }

                    2 -> {
                        bodyPartFragment.mLegIds = selectImageIndex(position)
                    }
                }
                refreshFragment(bodyPartFragment)
            }

        } ?: let {

            button!!
            bodyPartFragmentContainerPhoneLayout!!

            savedInstanceState?.let {
                mIndexHead = it.getInt(BodyPartFragment.HEAD_INDEX)
                mIndexBody = it.getInt(BodyPartFragment.BODY_INDEX)
                mIndexLeg = it.getInt(BodyPartFragment.LEG_INDEX)
                isNextButtonClicked = it.getBoolean(IS_BTN_CLICKED)

            }


            if (isNextButtonClicked)
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    gridView
                )

            childFragmentManager.findFragmentByTag(BodyPartFragment.TAG)?.run {
                bodyPartFragment = this as BodyPartFragment
            } ?: run {
                bodyPartFragment = BodyPartFragment()
                bodyPartFragment.mBodyList = AndroidImageAsset.bodyList
                bodyPartFragment.mHeadList = AndroidImageAsset.headList
                bodyPartFragment.mLegList = AndroidImageAsset.legList
            }



            gridView.setOnItemClickListener { _, _, position, _ ->
                when (position / 12) {
                    0 -> {
                        bodyPartFragment.mHeadIds = selectImageIndex(position)
                    }
                    1 -> {
                        bodyPartFragment.mBodyIds = selectImageIndex(position)
                    }

                    2 -> {
                        bodyPartFragment.mLegIds = selectImageIndex(position)
                    }
                }
            }

            button.setOnClickListener {
                isNextButtonClicked = true
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    gridView
                )

                if (bodyPartFragment.isAdded)
                    refreshFragment(bodyPartFragment)

                childFragmentManager.commit {
                    if (bodyPartFragment.isAdded)
                        return@commit
                    // Attach or add a fragment
                    if (bodyPartFragment.isDetached) {
                        attach(bodyPartFragment)
                    } else {
                        Log.d("FragmentManager", "add")
                        add(
                            bodyPartFragmentContainerPhoneLayout.id,
                            bodyPartFragment,
                            BodyPartFragment.TAG
                        )
                        setReorderingAllowed(true)
                    }
                    // Set the animation for this transaction
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

                }


            }


            gridView.adapter = MasterListAdapter(requireContext(), mBodyPartList)


        }

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
        outState.putInt(BodyPartFragment.HEAD_INDEX, mIndexHead)
        outState.putInt(BodyPartFragment.BODY_INDEX, mIndexBody)
        outState.putInt(BodyPartFragment.LEG_INDEX, mIndexLeg)
        outState.putBoolean(IS_BTN_CLICKED, isNextButtonClicked)
    }


}