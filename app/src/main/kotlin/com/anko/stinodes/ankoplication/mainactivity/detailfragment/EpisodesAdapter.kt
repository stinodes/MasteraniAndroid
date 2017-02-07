package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.EpisodeViewHolderUI
import org.jetbrains.anko.AnkoContext

class EpisodesAdapter(
        val context: Context,
        items: List<Episode>
//        val onReleaseClicked: (Release) -> Unit
): RecyclerView.Adapter<EpisodeViewHolder>() {

    private var _items: MutableList<Episode> = mutableListOf()
    val items: List<Episode>
        get() = listOf(*_items.toTypedArray())

    init {
        replaceItems(items)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder?, position: Int) {
        holder?.ctx = context
        holder?.onBind(getItem(position))
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EpisodeViewHolder {
        val ui = EpisodeViewHolderUI()
        return EpisodeViewHolder(
            ui.createView(
                    AnkoContext.Companion.create(context, parent)
            ),
            ui
        )
    }

    override fun getItemCount(): Int = items.size

    fun replaceItems(newItems: List<Episode>) {
        _items = mutableListOf(*newItems.toTypedArray())
        notifyDataSetChanged()
    }

    fun getItem(pos: Int) = items[pos]
}