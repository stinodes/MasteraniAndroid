package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.AnimeFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AnimeFragmentUI: AnkoComponent<AnimeFragment> {

    lateinit var recycler: RecyclerView

    override fun createView(ui: AnkoContext<AnimeFragment>): View  = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent) {}
//            backgroundResource = R.drawable.dark_background_gradient
            recycler = recyclerView {
                topPadding = dip(4)
                clipToPadding = false
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}