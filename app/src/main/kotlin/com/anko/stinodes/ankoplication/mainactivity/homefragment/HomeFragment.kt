package com.anko.stinodes.ankoplication.mainactivity.homefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment.FragmentView.Anime
import com.anko.stinodes.ankoplication.mainactivity.homefragment.HomeFragment.FragmentView.Releases
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.AnimeFragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ReleasesFragment
import com.anko.stinodes.ankoplication.mainactivity.homefragment.ui.HomeFragmentUI
import com.anko.stinodes.ankoplication.util.FragmentAdapter
import org.jetbrains.anko.AnkoContext

class HomeFragment(val args: Bundle): Fragment() {

    val ui = HomeFragmentUI()

    enum class FragmentView {
        Releases,
        Anime
    }

    companion object {
        fun create(bundle: Bundle = Bundle()): HomeFragment {
            val fragment = HomeFragment(bundle)
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = ui.createView(
                AnkoContext.create(activity, this)
        )

        val adapter = FragmentAdapter(childFragmentManager)
                .add(getFragment(Releases), "Releases")
                .add(getFragment(Anime), "Anime")
        ui.pager.adapter = adapter

        return view
    }

    override fun onResume() {
        with (activity as MainActivity) {
            ui.tabs.setupWithViewPager(this@HomeFragment.ui.pager)
            ui.expandTabs()
        }
        super.onResume()
    }
    override fun onPause() {
//        (activity as MainActivity).ui.collapseTabs()
        super.onPause()
    }

    fun getFragment(view: HomeFragment.FragmentView, bundle: Bundle = Bundle()) = when(view) {
        (Releases)-> ReleasesFragment.create(bundle)
        (Anime)-> AnimeFragment.create(bundle)
        else -> ReleasesFragment.create(bundle)
    }
}