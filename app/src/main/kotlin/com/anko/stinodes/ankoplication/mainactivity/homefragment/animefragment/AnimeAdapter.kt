package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui.AnimeViewHolderUI
import com.anko.stinodes.ankoplication.web.MAWrapper
import org.jetbrains.anko.AnkoContext

class AnimeAdapter(
        val context: Context,
        val onAnimeClicked: (Anime) -> Unit
): RecyclerView.Adapter<AnimeViewHolder>() {

    val _items: MutableList<Anime> = mutableListOf()
    val items: List<Anime>
        get() = listOf(*_items.toTypedArray())

    var isFetching = false
    var page: Int = 0
    var nPerPage: Int = 56

    init {
        addPage(1)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder?, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder?.ctx = context
            holder?.onBind(item)
            if (isLast(position))
                addPage(page + 1)
        }
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

    fun addPage(newPage: Int) {
        if (!isFetching) {
            isFetching = true
            MAWrapper.get().animeObservable(newPage)
                    .subscribe(
                            {
                                isFetching = false
                                addPage(it.data!!, newPage)
                            },
                            {}
                    )
        }
    }

    fun addPage(newItems: List<Anime>, newPage: Int, oldPage: Int = page) {
        _items.addAll(newItems)
        page = newPage
        notifyItemRangeInserted(oldPage * nPerPage, page * nPerPage)
    }

    fun getItem(pos: Int): Anime? =
            if (items.isNotEmpty()) items[pos]
            else null

    override fun getItemCount(): Int = page * nPerPage
    fun isLast(pos: Int) = pos == itemCount - 1
}