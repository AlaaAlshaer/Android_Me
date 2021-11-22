package com.example.andriod_me.helper

import androidx.fragment.app.Fragment
import com.example.andriod_me.R
import com.example.andriod_me.data.Image

import com.example.andriod_me.ui.DashboardFragment
import com.example.andriod_me.ui.HomeFragment
import com.example.andriod_me.ui.NotificationsFragment
import com.example.andriod_me.ui.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.home),
    DASHBOARD(1, R.id.dashboard),
    NOTIFICATIONS(2, R.id.notifications),
    PROFILE(3, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.DASHBOARD.id -> BottomNavigationPosition.DASHBOARD
    BottomNavigationPosition.NOTIFICATIONS.id -> BottomNavigationPosition.NOTIFICATIONS
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(
    mBodyPartList: List<Int>,
    mImageArrayList: ArrayList<Image>
): Fragment = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.newInstance(mBodyPartList)
    BottomNavigationPosition.DASHBOARD -> DashboardFragment.newInstance(mImageArrayList)
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.DASHBOARD -> DashboardFragment.TAG
    BottomNavigationPosition.NOTIFICATIONS -> NotificationsFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}

