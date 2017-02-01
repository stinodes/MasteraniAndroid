package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui.AnimeFragmentUI
import com.anko.stinodes.ankoplication.web.MAWrapper
import io.realm.Realm
import org.jetbrains.anko.AnkoContext
import kotlin.properties.Delegates

class AnimeFragment(val args: Bundle): Fragment() {

    val ui = AnimeFragmentUI()

    private var realm: Realm by Delegates.notNull()

    companion object {
        fun create(bundle: Bundle = Bundle()): AnimeFragment {
            val fragment = AnimeFragment(bundle)
            return fragment
        }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MAWrapper.get().fetchAnime(page = 1, order = MAWrapper.Order.SCORE_DESC)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = ui.createView(
                AnkoContext.create(activity, this)
        )

        ui.recycler.adapter = AnimeAdapter(
                activity,
                { anime ->
                    (activity as MainActivity).navigate(MainActivity.FragmentView.Detail, {
                        val b = Bundle()
                        b.putParcelable("data", EpisodeParcelable.fromId(anime.id!!))
                        b
                    }())
                }
        )
        ui.recycler.layoutManager = GridLayoutManager(activity, 3, GridLayout.VERTICAL, false)

        return view
    }

}