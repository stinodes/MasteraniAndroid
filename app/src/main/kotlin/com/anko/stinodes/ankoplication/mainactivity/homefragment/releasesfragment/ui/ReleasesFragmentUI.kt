package com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ReleasesFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ReleasesFragmentUI() : AnkoComponent<ReleasesFragment> {

    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ReleasesFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent) {}
//            backgroundResource = R.drawable.dark_background_gradient
            recyclerView = recyclerView {
                topPadding = dip(4)
                rightPadding = dip(4)
                leftPadding = dip(4)
                clipToPadding = false
            }.lparams(width = matchParent, height = matchParent) {}
        }
    }
}
