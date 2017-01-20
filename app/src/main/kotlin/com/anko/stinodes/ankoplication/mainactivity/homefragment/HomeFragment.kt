package com.anko.stinodes.ankoplication.mainactivity.homefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.mainactivity.homefragment.ui.HomeFragmentUI
import io.realm.Realm
import io.realm.Sort
import org.jetbrains.anko.AnkoContext
import kotlin.properties.Delegates

class HomeFragment(val args: Bundle): Fragment() {

    val ui = HomeFragmentUI()

    private var realm: Realm by Delegates.notNull()

    companion object {
        fun create(bundle: Bundle = Bundle()): HomeFragment {
            val fragment = HomeFragment(bundle)
            return fragment
        }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = ui.createView(
                AnkoContext.Companion.create(activity, this)
        )
        ui.recyclerView.adapter = ReleasesAdapter(
                activity,
                realm.where(Release::class.java).findAllSortedAsync("createdAt", Sort.DESCENDING),
                { release ->
                    //Todo: navigate
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