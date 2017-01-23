package com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.EpisodesFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EpisodesFragmentUI: AnkoComponent<EpisodesFragment> {
    lateinit var recycler: RecyclerView
    override fun createView(ui: AnkoContext<EpisodesFragment>): View = with(ui) {
        recyclerView {
            recycler = this
            lparams(width = matchParent, height = matchParent)
        }
    }
}