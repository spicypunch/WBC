package com.example.wbc.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = ArrayList<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = fragments[position]

//    fun refreshFragment(index: Int, fragment: Fragment) {
//        fragments[index] = fragment
//        notifyItemChanged(index)
//    }
}