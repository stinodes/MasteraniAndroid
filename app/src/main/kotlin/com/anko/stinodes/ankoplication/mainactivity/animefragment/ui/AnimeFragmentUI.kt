package com.anko.stinodes.ankoplication.mainactivity.animefragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.mainactivity.animefragment.AnimeFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

class AnimeFragmentUI: AnkoComponent<AnimeFragment> {

    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<AnimeFragment>): View  = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent) {}

//            recyclerView = recyclerView {}
//                    .lparams(width = matchParent) {
//                        weight = 1f
//                    }
        }
    }
}