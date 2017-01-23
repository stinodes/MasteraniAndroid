package com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.ui.EpisodesFragmentUI
import org.jetbrains.anko.AnkoContext

class EpisodesFragment(val args: Bundle): Fragment() {

    val ui = EpisodesFragmentUI()
    var episodes: List<Episode>? = null

    companion object {
        fun create(bundle: Bundle = Bundle()): EpisodesFragment {
            val fragment = EpisodesFragment(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ui.createView(
                AnkoContext.Companion.create(context, this)
        )
    }

}