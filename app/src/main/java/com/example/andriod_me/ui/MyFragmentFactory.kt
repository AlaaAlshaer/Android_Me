package com.example.andriod_me.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MyFragmentFactory(private val mMasterListAdapter: MasterListAdapter) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            MasterListFragment::class.java -> MasterListFragment(mMasterListAdapter)
            else -> super.instantiate(classLoader, className)
        }


}