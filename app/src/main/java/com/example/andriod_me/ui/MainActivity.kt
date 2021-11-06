package com.example.andriod_me.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log



import androidx.appcompat.app.AppCompatActivity


import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset


class MainActivity : AppCompatActivity(), OnImageClickListener, OnButtonClickListener {

    private var headIndex = 0
    private var bodyIndex = 0
    private var legIndex = 0

    companion object {
        const val HEAD_INDEX = "headIndex"
        const val BODY_INDEX = "bodyIndex"
        const val LEG_INDEX = "legIndex"
    }

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

    override fun onSelectedImage(position: Int) {

        val selectIndex: Int = position / 12

        val listIndex: Int = position - 12 * selectIndex

        when (selectIndex) {
            0 -> headIndex = listIndex
            1 -> bodyIndex = listIndex
            2 -> legIndex = listIndex
        }

    }

    override fun onClickButton() {
        val bundle = Bundle()
        bundle.putInt(HEAD_INDEX, headIndex)
        bundle.putInt(BODY_INDEX, bodyIndex)
        bundle.putInt(LEG_INDEX, legIndex)


        val intent = Intent(

            this, AndroidMeActivity::class.java
        )
        intent.putExtras(bundle)

        Log.d("MainActivity", "next btn clicked")
        startActivity(intent)
    }

}