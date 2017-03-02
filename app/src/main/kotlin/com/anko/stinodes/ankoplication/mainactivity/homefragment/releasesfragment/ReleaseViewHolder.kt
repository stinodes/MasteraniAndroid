package com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ui.ReleaseViewHolderUI

class ReleaseViewHolder(
        view: View,
        val ui: ReleaseViewHolderUI,
        val onReleaseClicked: (Release) -> Unit
): RecyclerView.ViewHolder(view) {

    var ctx: Context? = null

    fun onBind(release: Release?) {
        ui.release = release
        ui.onReleaseClicked = onReleaseClicked
        ui.title?.text = release?.anime?.title
        ui.episode?.text = "Episode ${release?.episode}"
        ui.bindWallpaperImage(ctx!!)
    }
}