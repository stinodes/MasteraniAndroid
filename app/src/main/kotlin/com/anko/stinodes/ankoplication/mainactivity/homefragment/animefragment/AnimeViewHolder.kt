package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui.AnimeViewHolderUI
import java.text.SimpleDateFormat
import java.util.*

class AnimeViewHolder(
        view: View,
        val ui: AnimeViewHolderUI,
        val onAnimeClicked: (Anime) -> Unit
): RecyclerView.ViewHolder(view) {

    var ctx: Context? = null

    fun onBind(anime: Anime?) {
        val dateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
        ui.anime = anime
        ui.onAnimeClicked = onAnimeClicked
        ui.bindWallpaperImage(ctx!!)
        ui.bindRating(anime?.score!!)
        ui.title?.text = anime?.title
        ui.year?.text = dateFormat.format(anime?.startedAiring)
    }
}