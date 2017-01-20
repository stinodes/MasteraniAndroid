package com.anko.stinodes.ankoplication.mainactivity.homefragment

import android.content.Context
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.mainactivity.homefragment.ui.ReleaseViewHolderUI
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import org.jetbrains.anko.AnkoContext

class ReleasesAdapter(
        context: Context,
        results: OrderedRealmCollection<Release>,
        val onReleaseClicked: (Release) -> Unit
): RealmRecyclerViewAdapter<Release, ReleaseViewHolder>(
        context,
        results,
        true
) {
    override fun onBindViewHolder(holder: ReleaseViewHolder?, position: Int) {
        holder?.ctx = context
        holder?.onBind(getItem(position))
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ReleaseViewHolder {
        val ui = ReleaseViewHolderUI()
        return ReleaseViewHolder(
                ui.createView(
                        AnkoContext.Companion.create(
                                context,
                                parent
                        )
                ),
                ui,
                onReleaseClicked
        )
    }
}