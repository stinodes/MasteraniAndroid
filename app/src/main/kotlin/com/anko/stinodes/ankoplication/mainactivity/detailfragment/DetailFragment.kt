package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.DetailFragmentUI
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import io.realm.Realm
import org.jetbrains.anko.AnkoContext
import kotlin.properties.Delegates

class DetailFragment(val args: Bundle): Fragment() {

    val ui = DetailFragmentUI()

    private var realm: Realm by Delegates.notNull()
    lateinit var release: Release

    companion object {
        fun create(bundle: Bundle = Bundle()): DetailFragment {
            val fragment = DetailFragment(bundle)
            return fragment
        }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity)
                .ui.appBar.setExpanded(true, true)

        val o: EpisodeParcelable = args.getParcelable("data")
        realm.where(Release::class.java)
                .equalTo("anime.id", o.animeId)
                .equalTo("episode", o.episode)
                .findFirstAsync()
                .asObservable<Release>()
                .filter(Release::isLoaded)
                .subscribe(
                        {
                            bindRelease(it)
                        },
                        Throwable::printStackTrace
                )
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return ui.createView(
                AnkoContext.Companion.create(activity, this)
        )
    }

    fun bindRelease(release: Release) {
        Log.d("RELEASE DETAIL", "${release.episode}")
        this.release = release
        (activity as MainActivity)
                .ui.showAppBarImage(activity, "${IMAGE_URL}wallpaper/2/${release.anime?.wallpaper!!}")
    }

    override fun onDestroy() {
        (activity as MainActivity).ui.hideAppBarImage()
        super.onDestroy()
    }

}