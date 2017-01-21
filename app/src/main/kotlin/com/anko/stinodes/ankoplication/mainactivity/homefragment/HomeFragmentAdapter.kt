package com.anko.stinodes.ankoplication.mainactivity.homefragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HomeFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val fragments: MutableList<Fragment> = mutableListOf()
    val titles: MutableList<String> = mutableListOf()

    fun add(fr: Fragment, title: String): HomeFragmentAdapter {
        fragments.add(fr)
        titles.add(title)
        return this
    }
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence = titles[position]
    override fun getCount(): Int = fragments.size
}