package com.example.edvorainternshiptask.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.edvorainternshiptask.fragments.NearestRidesFragment
import com.example.edvorainternshiptask.fragments.PastRidesFragment
import com.example.edvorainternshiptask.fragments.UpcomingRidesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle){

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        // returns respective fragments for respective viewpager positions
        when(position) {
            0-> return NearestRidesFragment()
            1-> return UpcomingRidesFragment()
            2-> return PastRidesFragment()
        }
        return NearestRidesFragment()
    }

    companion object {
        private const val numberOfTabs = 3 // nearest, upcoming and past rides
//        val nearestRidesFragment = NearestRidesFragment()
//        val upcomingRidesFragment = UpcomingRidesFragment()
//        val pastRidesFragment = PastRidesFragment()
    }


}