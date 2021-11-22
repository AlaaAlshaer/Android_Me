package com.example.andriod_me.data

import com.example.andriod_me.R

class AndroidImageAsset {

    companion object{

         val headList : ArrayList<Int> = arrayListOf(
            R.drawable.head1,
            R.drawable.head2,
            R.drawable.head3,
            R.drawable.head4,
            R.drawable.head5,
            R.drawable.head6,
            R.drawable.head7,
            R.drawable.head8,
            R.drawable.head9,
            R.drawable.head10,
            R.drawable.head11,
            R.drawable.head12,
        )


         val bodyList : ArrayList<Int> = arrayListOf(
            R.drawable.body1,
            R.drawable.body2,
            R.drawable.body3,
            R.drawable.body4,
            R.drawable.body5,
            R.drawable.body6,
            R.drawable.body7,
            R.drawable.body8,
            R.drawable.body9,
            R.drawable.body10,
            R.drawable.body11,
            R.drawable.body12,
        )

         val legList : ArrayList<Int> = arrayListOf(
            R.drawable.legs1,
            R.drawable.legs2,
            R.drawable.legs3,
            R.drawable.legs4,
            R.drawable.legs5,
            R.drawable.legs6,
            R.drawable.legs7,
            R.drawable.legs8,
            R.drawable.legs9,
            R.drawable.legs10,
            R.drawable.legs11,
            R.drawable.legs12,
        )

        val allPart =  headList + bodyList + legList

        val ImageArray = arrayListOf(
            Image(
                R.drawable.legs1,
                "legs1"
            ),
            Image(
                R.drawable.legs2,
                "legs2"
            ),
            Image(
                R.drawable.legs3,
                "legs3"
            ),
            Image(
                R.drawable.legs4,
                "legs4"
            ),
            Image(
                R.drawable.legs5,
                "legs5"
            ),
            Image(
                R.drawable.legs6,
                "legs6"
            ),
            Image(
                R.drawable.legs7,
                "legs7"
            ),
            Image(
                R.drawable.legs8,
                "legs8"
            ),
            Image(
                R.drawable.legs9,
                "legs9"
            ),
            Image(
                R.drawable.legs10,
                "legs10"
            ),
        )

    }

}