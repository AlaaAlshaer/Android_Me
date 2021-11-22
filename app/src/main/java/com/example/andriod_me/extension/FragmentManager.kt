package com.example.andriod_me.extension

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.andriod_me.R
import com.example.andriod_me.ui.HomeFragment


fun FragmentManager.switchFragment(fragment: Fragment, tag: String): Boolean {
    if (fragment.isAdded) return false
    commit {
        // Detach a fragment
        findFragmentById(R.id.container)?.also {
            detach(it)
        }
        // Attach or add a fragment
        if (fragment.isDetached) {
            attach(fragment)
            Log.d("FragmentManager","attach")
        } else {
            Log.d("FragmentManager","add")
            add(R.id.container, fragment, tag)
        }
        // Set the animation for this transaction
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

    }
    // Immediately execute transactions
    return executePendingTransactions()
}