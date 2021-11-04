package com.example.andriod_me.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val masterListFragment = MasterListFragment()
        masterListFragment.setBodyPartsList(AndroidImageAsset.allPart)

        val supportFragmentManager = supportFragmentManager

        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_master_list_container, masterListFragment)
            .commit()
    }
}