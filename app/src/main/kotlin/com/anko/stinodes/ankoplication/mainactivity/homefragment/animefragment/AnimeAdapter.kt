package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment

import android.content.Context
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui.AnimeViewHolderUI
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import org.jetbrains.anko.AnkoContext

class AnimeAdapter(
        context: Context,
        results: OrderedRealmCollection<Anime>,
        val onAnimeClicked: (Anime) -> Unit
): RealmRecyclerViewAdapter<Anime, AnimeViewHolder>(
        context,
        results,
        true
) {
    override fun onBindViewHolder(holder: AnimeViewHolder?, position: Int) {
        holder?.ctx = context
        holder?.onBind(getItem(position))
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): AnimeViewHolder {
        val ui = AnimeViewHolderUI()
        return AnimeViewHolder(
                ui.createView(
                        AnkoContext.create(
                                context,
                                parent
                        )
                ),
                ui,
                onAnimeClicked
        )
    }
}