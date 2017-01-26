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
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnimeInfo
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.DetailFragmentUI
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.web.MAWrapper
import org.jetbrains.anko.AnkoContext
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailFragment(val args: Bundle): Fragment() {

    val ui = DetailFragmentUI()

    enum class FragmentView {
        Info,
        Episodes
    }

    var anime: DetailedAnime? = null
    var episode: Episode? = null


    companion object {
        fun create(bundle: Bundle = Bundle()): DetailFragment {
            val fragment = DetailFragment(bundle)
            return fragment
        }
    }

    init {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity)
                .ui.appBar.setExpanded(true, true)

        val o: EpisodeParcelable = args.getParcelable("data")
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
    )
            : View
            = ui.createView(
                AnkoContext.Companion.create(activity, this)
        )

    override fun onResume() {
        (activity as MainActivity).ui.collapseTabs()
        super.onResume()
    }
    override fun onDestroy() {
        (activity as MainActivity).ui.hideAppBarImage()
        super.onDestroy()
    }

    fun bindDetailData(anime: DetailedAnime) {
        this.anime = anime
        Log.d("Detailed Anime", "${anime.info!!.title}")
        (activity as MainActivity)
                .ui.showAppBarImage(activity, "${IMAGE_URL}wallpaper/2/${anime.wallpapers!![0].file!!}")
        bindDetailData(anime.info!!)
        bindEpisodeData(anime.episodes!!)
    }
    fun bindDetailData(anime: DetailedAnimeInfo) {
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
    fun bindEpisodeData(episodes: List<Episode>) {
        ui.episodeRecycler.adapter =
                EpisodesAdapter(
                        activity,
                        episodes
                )
        ui.episodeRecycler.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}