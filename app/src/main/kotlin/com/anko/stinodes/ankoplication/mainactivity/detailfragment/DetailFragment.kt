package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnime
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.DetailFragmentUI
import com.anko.stinodes.ankoplication.web.MAWrapper
import org.jetbrains.anko.AnkoContext
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailFragment(val args: Bundle): Fragment() {

    val ui = DetailFragmentUI()

    var anime: DetailedAnime? = null
    var selectedEpisode: Int? = null
    var episode: Episode? = null

    companion object {
        fun create(bundle: Bundle = Bundle()): DetailFragment {
            val fragment = DetailFragment(bundle)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val o: EpisodeParcelable = args.getParcelable("data")
        selectedEpisode = o.episode

        MAWrapper.get().api
                .getDetailedAnimeId(o.animeId!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            bindDetailData(it)
                        },
                        Throwable::printStackTrace
                )
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = ui.createView(
                AnkoContext.Companion.create(activity, this)
        )

        return view
    }

    override fun onResume() {
        with (activity as MainActivity) {
            ui.collapseTabs()
            ui.appBar.setExpanded(false, true)
        }
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun bindDetailData(anime: DetailedAnime) {
        this.anime = anime
        Log.d("Detailed Anime", "${anime.info!!.title}")

        ui.bindCardImage(anime.getWallpaper().file!!, context)
        ui.bindInfo(anime)
        bindEpisodeData(anime.episodes!!)
    }

    fun bindEpisodeData(episodes: List<Episode> = listOf()) {
        ui.episodeRecycler.adapter =
                EpisodesAdapter(
                        activity,
                        episodes
                )
        ui.episodeRecycler.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        if (episodes.isEmpty())
            ui.episodeRecycler.visibility = View.GONE
        else
            ui.emptyMessage.visibility = View.GONE
    }
}