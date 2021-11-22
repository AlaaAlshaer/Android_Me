package com.example.andriod_me.ui


import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset
import com.example.andriod_me.databinding.ActivityMainBinding
import com.example.andriod_me.extension.active
import com.example.andriod_me.extension.switchFragment
import com.example.andriod_me.helper.*


class MainActivity : AppCompatActivity() {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory =
            MyFragmentFactory(AndroidImageAsset.allPart, AndroidImageAsset.ImageArray)
        super.onCreate(savedInstanceState)

        restoreSavedInstanceState(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.toolbar.apply {
            setSupportActionBar(this)
        }


        binding.bottomNavigation.apply {
            // Set a default position
            active(navPosition.position) // Extension function

            // Set a listener for handling selection events on bottom navigation items
            setOnItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                switchFragment(navPosition)
            }
        }

        // Add the home fragment
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Store the current navigation position.
        outState.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)?.also {
            navPosition = findNavigationPositionById(it)
        }
    }

    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            supportFragmentManager.switchFragment(it, navPosition.getTag()) // Extension function
        }
    }

    private fun findFragment(position: BottomNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment(
                AndroidImageAsset.allPart,
                AndroidImageAsset.ImageArray
            )
    }

    companion object {
        const val KEY_POSITION = "keyPosition"
    }


}