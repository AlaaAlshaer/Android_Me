package com.example.andriod_me.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        supportFragmentManager.fragmentFactory =
            MyFragmentFactory(MasterListAdapter(this, listOf()))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout =
            findViewById<LinearLayout>(R.id.activity_android_me_liner_layout)

        mPaneTwo = linearLayout != null

        if (savedInstanceState == null) {
            val masterListFragment =
                MasterListFragment(MasterListAdapter(this, AndroidImageAsset.allPart))
            masterListFragment.mBtnState = mPaneTwo

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.activity_main_master_list_container, masterListFragment)
                .commit()
        }


        if (mPaneTwo) {
            if (savedInstanceState == null) {
                val headFragment = BodyPartFragment()
                val bodyFragment = BodyPartFragment()
                val legFragment = BodyPartFragment()

                headFragment.setMListIndex(AndroidImageAsset.headList)
                bodyFragment.setMListIndex(AndroidImageAsset.bodyList)
                legFragment.setMListIndex(AndroidImageAsset.legList)

                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.leg_container, legFragment)
                    .commit()
            }
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
                        .replace(R.id.head_container, newFragment)
                        .commit()
                }
                1 -> {
                    newFragment.setMListIndex(AndroidImageAsset.bodyList)
                    manager.beginTransaction()
                        .replace(R.id.body_container, newFragment)
                        .commit()
                }
                2 -> {
                    newFragment.setMListIndex(AndroidImageAsset.legList)
                    manager.beginTransaction()
                        .replace(R.id.leg_container, newFragment)
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