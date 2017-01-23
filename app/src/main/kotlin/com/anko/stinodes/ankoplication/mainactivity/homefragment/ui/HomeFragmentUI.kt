package com.anko.stinodes.ankoplication.mainactivity.homefragment.ui

import android.support.v4.view.ViewPager
import android.view.View
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager

class HomeFragmentUI: AnkoComponent<HomeFragment> {

    companion object {
        val VIEW_PAGER = R.id.home_viewpager
    }
    lateinit var pager: ViewPager

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
            viewPager {
                id = VIEW_PAGER
                lparams {
                    width = matchParent
                    height = matchParent
                }

                pager = this
            }
    }
}