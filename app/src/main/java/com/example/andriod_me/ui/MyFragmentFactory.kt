package com.example.andriod_me.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.andriod_me.data.AndroidImageAsset
import com.example.andriod_me.data.Image

class MyFragmentFactory(
    private val mBodyPartList: List<Int>,
    private val mImageArrayList: ArrayList<Image>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            HomeFragment::class.java -> HomeFragment(mBodyPartList)
            DashboardFragment::class.java -> DashboardFragment(mImageArrayList)
            else -> super.instantiate(classLoader, className)
        }

    }

}