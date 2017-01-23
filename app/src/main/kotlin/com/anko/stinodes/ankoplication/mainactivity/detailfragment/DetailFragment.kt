package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnime
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment.FragmentView.Episodes
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment.FragmentView.Info
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.EpisodesFragment
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.InfoFragment
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.DetailFragmentUI
import com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ReleasesFragment
import com.anko.stinodes.ankoplication.util.FragmentAdapter
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
    lateinit var infoFragment: InfoFragment

    var episode: Episode? = null
    lateinit var episodesFragment: EpisodesFragment


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
                            bindData(it)
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
        infoFragment = getFragment(Info) as InfoFragment
        episodesFragment = getFragment(Episodes) as EpisodesFragment


        val adapter = FragmentAdapter(childFragmentManager)
                .add(infoFragment, "Info")
                .add(episodesFragment, "Episodes")
        ui.pager.adapter = adapter

        (activity as MainActivity).ui.expandTabs()

        return view
    }
    override fun onResume() {
        (activity as MainActivity).ui.tabs.setupWithViewPager(ui.pager)
        super.onResume()
    }
    override fun onDestroy() {
        (activity as MainActivity).ui.hideAppBarImage()
        super.onDestroy()
    }

    fun bindData(anime: DetailedAnime) {
        this.anime = anime
        Log.d("Detailed Anime", "${anime.info!!.title}")
        (activity as MainActivity)
                .ui.showAppBarImage(activity, "${IMAGE_URL}wallpaper/2/${anime.wallpapers!![0].file!!}")
        infoFragment.bindData(anime.info!!)

    }
    fun getFragment(view: FragmentView, bundle: Bundle = Bundle()) = when(view) {
        (Info)-> InfoFragment.create(bundle)
        (Episodes)-> EpisodesFragment.create(bundle)
        else -> ReleasesFragment.create(bundle)
    }

}