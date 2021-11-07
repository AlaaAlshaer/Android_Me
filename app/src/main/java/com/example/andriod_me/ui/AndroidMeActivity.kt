package com.example.andriod_me.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import com.example.andriod_me.R
import com.example.andriod_me.data.AndroidImageAsset

class AndroidMeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)

        val intent = intent
        val headIndex = intent?.getIntExtra(MainActivity.HEAD_INDEX, 0) ?: 0
        val bodyIndex = intent?.getIntExtra(MainActivity.BODY_INDEX, 0) ?: 0
        val legIndex = intent?.getIntExtra(MainActivity.LEG_INDEX, 0) ?: 0

        if (savedInstanceState == null) {
            val headFragment = BodyPartFragment()
            val bodyFragment = BodyPartFragment()
            val legFragment = BodyPartFragment()

            headFragment.setMListIndex(AndroidImageAsset.headList)
            headFragment.setMImageIds(headIndex)
            bodyFragment.setMListIndex(AndroidImageAsset.bodyList)
            bodyFragment.setMImageIds(bodyIndex)
            legFragment.setMListIndex(AndroidImageAsset.legList)
            legFragment.setMImageIds(legIndex)

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.head_container, headFragment)
                .add(R.id.body_container, bodyFragment)
                .add(R.id.leg_container, legFragment)
                .commit()

        }
    }
}
