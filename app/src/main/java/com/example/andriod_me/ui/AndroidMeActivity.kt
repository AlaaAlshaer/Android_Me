package com.example.andriod_me.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset

class AndroidMeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)

        val intent = intent
        val headIndex = intent.getIntExtra(MainActivity.HEAD_INDEX,0)
        val bodyIndex = intent.getIntExtra(MainActivity.BODY_INDEX,0)
        val legIndex = intent.getIntExtra(MainActivity.LEG_INDEX,0)

        if (savedInstanceState == null) {
            val headFragment = BodyPartFragment()
            val bodyFragment = BodyPartFragment()
            val legFragment = BodyPartFragment()

            val supportFragmentManager = supportFragmentManager

            headFragment.setMListIndex(AndroidImageAsset.headList)
            headFragment.setMImageIds(headIndex)
            bodyFragment.setMListIndex(AndroidImageAsset.bodyList)
            bodyFragment.setMImageIds(bodyIndex)
            legFragment.setMListIndex(AndroidImageAsset.legList)
            legFragment.setMImageIds(legIndex)

            supportFragmentManager.beginTransaction()
                .add(R.id.activity_android_me_fl_head_container, headFragment)
                .commit()

            supportFragmentManager.beginTransaction()
                .add(R.id.activity_android_me_fl_body_container, bodyFragment)
                .commit()

            supportFragmentManager.beginTransaction()
                .add(R.id.activity_android_me_fl_leg_container, legFragment)
                .commit()

        }
    }
}
