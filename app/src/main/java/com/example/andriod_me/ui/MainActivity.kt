package com.example.andriod_me.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.LinearLayout


import androidx.appcompat.app.AppCompatActivity


import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset


class MainActivity : AppCompatActivity(), OnImageClickListener, OnButtonClickListener {

    private var headIndex = 0
    private var bodyIndex = 0
    private var legIndex = 0
    private var mPaneTwo = false

    companion object {
        const val HEAD_INDEX = "headIndex"
        const val BODY_INDEX = "bodyIndex"
        const val LEG_INDEX = "legIndex"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout =
            findViewById<LinearLayout>(R.id.activity_android_me_liner_layout)

        if (linearLayout != null) {
            mPaneTwo = true

            val btn = findViewById<Button>(R.id.fragment_master_list_btn_next)
            btn?.visibility = View.GONE

            val gridView = findViewById<GridView>(R.id.fragment_master_list_gv)
            gridView?.numColumns = 2

            if (savedInstanceState == null) {
                val headFragment = BodyPartFragment()
                val bodyFragment = BodyPartFragment()
                val legFragment = BodyPartFragment()

                headFragment.setMListIndex(AndroidImageAsset.headList)
                bodyFragment.setMListIndex(AndroidImageAsset.bodyList)
                legFragment.setMListIndex(AndroidImageAsset.legList)

                val manager = supportFragmentManager
                manager.beginTransaction()
                    .add(R.id.activity_android_me_fl_head_container, headFragment)
                    .commit()

                manager.beginTransaction()
                    .add(R.id.activity_android_me_fl_body_container, bodyFragment)
                    .commit()

                manager.beginTransaction()
                    .add(R.id.activity_android_me_fl_leg_container, legFragment)
                    .commit()
            }

        } else {
            mPaneTwo = false
        }
    }

    override fun onSelectedImage(position: Int) {

        val selectIndex: Int = position / 12

        val listIndex: Int = position - 12 * selectIndex

        if (mPaneTwo) {
            val newFragment = BodyPartFragment()
            newFragment.setMImageIds(listIndex)

            val manager = supportFragmentManager
            when (selectIndex) {
                0 -> {
                    newFragment.setMListIndex(AndroidImageAsset.headList)
                    manager.beginTransaction()
                        .replace(R.id.activity_android_me_fl_head_container, newFragment)
                        .commit()
                }
                1 -> {
                    newFragment.setMListIndex(AndroidImageAsset.bodyList)
                    manager.beginTransaction()
                        .replace(R.id.activity_android_me_fl_body_container, newFragment)
                        .commit()
                }
                2 -> {
                    newFragment.setMListIndex(AndroidImageAsset.legList)
                    manager.beginTransaction()
                        .replace(R.id.activity_android_me_fl_leg_container, newFragment)
                        .commit()
                }
            }
        } else {
            when (selectIndex) {
                0 -> headIndex = listIndex
                1 -> bodyIndex = listIndex
                2 -> legIndex = listIndex
            }
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