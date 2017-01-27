package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.detailedanime.Episode
import com.anko.stinodes.ankoplication.ext.asRoundedRect
import com.anko.stinodes.ankoplication.ext.aspectRatioFrameLayout
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout.Side.WIDTH
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class EpisodeViewHolderUI(): AnkoComponent<ViewGroup> {

    var episode: Episode? = null
    var image: ImageView? = null

    fun bindEpisodeImage(context: Context) {
        if (image != null && episode != null)
            Picasso.with(context)
                    .load("${IMAGE_URL}episodes/${episode!!.thumbnail}")
                    .fit()
                    .asRoundedRect(2.5f)
                    .into(image)
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with (ui) {
        relativeLayout {
            lparams(width = matchParent) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    marginStart = dip(4)
                    marginEnd = dip(4)
                }
                leftMargin = dip(4)
                rightMargin = dip(4)
                topMargin = dip(4)
                bottomMargin = dip(4)
            }
            aspectRatioFrameLayout {
                fixedSide = WIDTH
                aspectRatio = 0.56f

                image = imageView {
                    id = R.id.releaseWallpaper
                }
            }.lparams(width = matchParent)
        }
    }
}