package com.example.andriod_me.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.example.andriod_me.R


import com.example.andriod_me.databinding.FragmentDashboardBinding
import com.example.andriod_me.viewModel.DashboardFragmentViewModel


class DashboardFragment : Fragment(), ImageListAdapter.OnImageClickListener {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        enterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.fade)
        exitTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide_right)
        requireActivity().apply {
            title = getString(R.string.title_dashboard)
        }

        val rv = binding.fragmentImageListRv
        viewModel.imageList.observe(viewLifecycleOwner, {
            rv.adapter = ImageListAdapter(it, this)
        })

        return binding.root
    }


    companion object {
        val TAG: String = DashboardFragment::class.java.simpleName
        fun newInstance() = DashboardFragment()
    }

    init {
        Log.d(TAG, "DashboardFragment")
    }

    override fun onImageClick(position: Int) {
        Toast.makeText(context, "position = $position", Toast.LENGTH_SHORT).show()
    }

}