package com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.anko.stinodes.ankoplication.domain.EpisodeParcelable
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.mainactivity.MainActivity.FragmentView.Detail
import com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ui.ReleasesFragmentUI
import io.realm.Realm
import io.realm.Sort
import org.jetbrains.anko.AnkoContext
import kotlin.properties.Delegates

class ReleasesFragment(val args: Bundle): Fragment() {

    val ui = ReleasesFragmentUI()

    private var realm: Realm by Delegates.notNull()

    companion object {
        fun create(bundle: Bundle = Bundle()): ReleasesFragment {
            val fragment = ReleasesFragment(bundle)
            return fragment
        }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = ui.createView(
                AnkoContext.create(activity, this)
        )

        ui.recyclerView.adapter = ReleasesAdapter(
                activity,
                realm.where(Release::class.java).findAllSortedAsync("createdAt", Sort.DESCENDING),
                { release ->
                    (activity as MainActivity).navigate(Detail, {
                        val b = Bundle()
                        b.putParcelable("data", EpisodeParcelable.fromRelease(release))
                        b
                    }())
                }
        )
        ui.recyclerView.layoutManager = GridLayoutManager(activity, 2, GridLayout.VERTICAL, false)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}