package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnime
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnimeInfo
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Detail
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.DetailFragmentUI
import com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ToolbarImageFragment
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.web.MAWrapper
import org.jetbrains.anko.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailFragment(val args: Bundle): Fragment() {

    val ui = DetailFragmentUI()
    var toolbarImageFragment: ToolbarImageFragment? = null


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
            if (toolbarImageFragment == null) {
                toolbarImageFragment =
                        (activity as MainActivity)
                                .getToolbarFragment(Detail) as ToolbarImageFragment

                setToolbarFragment(toolbarImageFragment)
            }

            ui.collapseTabs()
            ui.appBar.setExpanded(true, true)
        }
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun bindDetailData(anime: DetailedAnime) {
        this.anime = anime
        Log.d("Detailed Anime", "${anime.info!!.title}")

        bindDetailData(anime.info!!)
        bindEpisodeData(anime.episodes!!)
        bindToolbarImage(anime.getWallpaper().file!!)

        if (selectedEpisode != null)
            ui.episodeRecycler.layoutManager.scrollToPosition(selectedEpisode!!)
    }
    fun bindDetailData(anime: DetailedAnimeInfo) {
        ui.titleView.text = anime.title
        ui.infoField(
                ui.infoContainer,
                "Average Rating",
                { viewGroup ->
                    with(viewGroup) {
                        linearLayout {

                            var i = 1
                            val mod = anime.score!! % 1

                            textView {
                                textColor = ContextCompat.getColor(context, R.color.white2)
                                alpha = 0.7f
                                textSize = 10f
                                setTypeface(typeface, Typeface.NORMAL)
                                text = "(${anime.score})"
                            }.lparams() {
                                setMargins(0, 0, 16, 0)
                                gravity = Gravity.CENTER_VERTICAL
                            }

                            while (i < anime.score!!) {

                                imageView {
                                    imageResource = R.drawable.star
                                }.lparams(height = dip(14), width = dip(14)) {
                                    setMargins(0, 0, 4, 0)
                                    gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                }

                                i++
                            }
                            if (mod > 0) {
                                frameLayout {
                                    imageView {
                                        imageResource = R.drawable.star
                                    }.lparams(height = dip(14), width = dip(14)) {
                                    }
                                }.lparams(width = dip(14 * mod)) {
                                    gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                    setMargins(0, 0, 4, 0)
                                }
                            }
                        }
                    }
                }
        )
        ui.infoField(
                ui.infoContainer,
                "Episodes",
                anime.episodesToString()
        )
        ui.description.text = anime.synopsis
    }
    fun bindEpisodeData(episodes: List<Episode>) {
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
    fun bindToolbarImage(url: String) {
        if (toolbarImageFragment != null )
            toolbarImageFragment!!.setImage(
                    "${IMAGE_URL}wallpaper/2/$url"
            )
    }
}