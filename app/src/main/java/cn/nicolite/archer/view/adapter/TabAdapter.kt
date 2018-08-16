package cn.nicolite.archer.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class TabAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>, private val titleList: List<String> = ArrayList())
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return if (titleList.isEmpty()) {
            super.getPageTitle(position)
        } else {
            titleList[position]
        }
    }

}