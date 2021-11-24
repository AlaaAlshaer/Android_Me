package com.example.andriod_me.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.andriod_me.R

import com.example.andriod_me.databinding.FragmentHomeBinding
import com.example.andriod_me.viewModel.HomeFragmentViewModel






class HomeFragment : Fragment(), ImageListAdapter.OnImageClickListener {

    private lateinit var binding: FragmentHomeBinding

    private var isBodyPartFragmentVisible = false
    private var isTabletMode = false

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        enterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.fade)
        exitTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide_right)

        requireActivity().apply {
            title = getString(R.string.title_home)
        }



        savedInstanceState?.let {
            isBodyPartFragmentVisible = it.getBoolean(IS_BTN_CLICKED)
        }

        val recyclerView = binding.fragmentMasterListRv
        isTabletMode = binding.fragmentHomeLine != null

        lateinit var bodyPartFragment: BodyPartFragment

        childFragmentManager.findFragmentByTag(BodyPartFragment.TAG)?.run {
            bodyPartFragment = this as BodyPartFragment
        } ?: run {
            bodyPartFragment = BodyPartFragment()
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
                    if (isBodyPartFragmentVisible) {
                        childFragmentManager.popBackStack()
                        hideBodyPartFragmentContainerPhoneLayout(
                            bodyPartFragmentContainerPhoneLayout,
                            button,
                            recyclerView
                        )
                        isBodyPartFragmentVisible = false
                    } else {
                        if (isEnabled) {
                            isEnabled = false
                            requireActivity().onBackPressed()
                        }
                    }
                })

            if (isBodyPartFragmentVisible)
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    recyclerView
                )


            button.setOnClickListener {
                isBodyPartFragmentVisible = true
                showBodyPartFragmentContainerPhoneLayout(
                    bodyPartFragmentContainerPhoneLayout,
                    button,
                    recyclerView
                )

                addFragment(
                    bodyPartFragment,
                    bodyPartFragmentContainerPhoneLayout.id,
                )


            }
        }


        viewModel.bodyPartList.observe(viewLifecycleOwner, {
            recyclerView.adapter = ImageListAdapter(it, this)
        })



        return binding.root
    }

    private fun hideBodyPartFragmentContainerPhoneLayout(
        f: FragmentContainerView,
        button: Button,
        recyclerView: RecyclerView
    ) {
        f.visibility = View.GONE
        button.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showBodyPartFragmentContainerPhoneLayout(
        f: FragmentContainerView,
        button: Button,
        recyclerView: RecyclerView
    ) {
        f.visibility = View.VISIBLE
        button.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        const val IS_BTN_CLICKED: String = "isClicked"
        fun newInstance() = HomeFragment()
    }

    init {
        Log.d(TAG, "init")
    }

    private fun selectImageIndex(position: Int): Int {
        val index: Int = position / 12
        return (position) - (12 * index)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_BTN_CLICKED, isBodyPartFragmentVisible)
    }

    private fun addFragment(fragment: BodyPartFragment, containerId: Int) {

        childFragmentManager.commit {
            setReorderingAllowed(true)
            if (!isTabletMode)
                addToBackStack(BodyPartFragment.TAG)
            /*setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )*/

            when {
                fragment.isAdded -> return@commit
                fragment.isDetached -> attach(fragment)
                else -> add(
                    containerId,
                    fragment,
                    BodyPartFragment.TAG
                )
            }
        }
    }

    override fun onImageClick(position: Int) {
        when (position / 12) {
            0 -> viewModel.selectHead(selectImageIndex(position))
            1 -> viewModel.selectBody(selectImageIndex(position))
            2 -> viewModel.selectLeg(selectImageIndex(position))
        }
    }


}