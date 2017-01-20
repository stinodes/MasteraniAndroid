package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent

class DetailFragmentUI: AnkoComponent<DetailFragment> {
    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)

        }
    }
}