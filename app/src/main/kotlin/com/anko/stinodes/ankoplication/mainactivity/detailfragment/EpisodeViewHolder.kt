package com.anko.stinodes.ankoplication.mainactivity.detailfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui.EpisodeViewHolderUI

class EpisodeViewHolder(
        view: View,
        val ui: EpisodeViewHolderUI
): RecyclerView.ViewHolder(view) {

    var ctx: Context? = null

    fun onBind(episode: Episode?) {
        ui.episode = episode
        ui.bindEpisodeImage(ctx!!)
    }
}