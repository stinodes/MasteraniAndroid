package com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnimeInfo
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.infofragment.ui.InfoFragmentUI
import org.jetbrains.anko.AnkoContext

class InfoFragment(val args: Bundle): Fragment() {

    val ui = InfoFragmentUI()
    var anime: DetailedAnimeInfo? = null

    companion object {
        fun create(bundle: Bundle = Bundle()): InfoFragment {
            val fragment = InfoFragment(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ui.createView(
                AnkoContext.Companion.create(context, this)
        )
    }

    fun bindData(anime: DetailedAnimeInfo) {
        this.anime = anime
        ui.titleView.text = anime.title
        ui.infoField(
                ui.infoContainer,
                "Average Rating",
                anime.scoreToString()
        )
        ui.infoField(
                ui.infoContainer,
                "Episodes",
                anime.episodesToString()
        )
    }
}