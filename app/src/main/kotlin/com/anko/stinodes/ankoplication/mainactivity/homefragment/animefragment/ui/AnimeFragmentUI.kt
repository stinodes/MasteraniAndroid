package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.AnimeFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.relativeLayout

class AnimeFragmentUI: AnkoComponent<AnimeFragment> {

    lateinit var recycler: RecyclerView

    override fun createView(ui: AnkoContext<AnimeFragment>): View  = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent) {}

            recycler = recyclerView {}.lparams(width = matchParent, height = matchParent)
        }
    }
}