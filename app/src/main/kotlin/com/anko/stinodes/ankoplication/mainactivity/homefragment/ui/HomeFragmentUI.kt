package com.anko.stinodes.ankoplication.mainactivity.homefragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class HomeFragmentUI() : AnkoComponent<HomeFragment> {

    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent) {
                backgroundResource = R.color.black
                topPadding = dip(4)
            }
            recyclerView = recyclerView {}
                    .lparams(width = matchParent, height = matchParent) {}
        }
    }
}
