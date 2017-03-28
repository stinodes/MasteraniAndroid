package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
    var imageContainer: ViewGroup? = null
    var image: ImageView? = null
    var title: TextView? = null
    var epNum: TextView? = null

    fun bindEpisodeImage(context: Context) {
        if (image != null && episode != null)
            Picasso.with(context)
                    .load("${IMAGE_URL}episodes/${episode!!.thumbnail}")
                    .fit()
                    .centerCrop()
                    .asRoundedRect(2.5f, topLeft = false, bottomLeft = false)
                    .into(image)
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with (ui) {
        linearLayout {
            lparams(width = matchParent) {
                bottomMargin = dip(12)
            }
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                elevation = 8f
            backgroundResource = R.drawable.dark_card

            linearLayout {
                gravity = Gravity.CENTER
                epNum = textView {
                    textSize = 16f
                    textColor = ContextCompat.getColor(context, R.color.white)
                    alpha = 0.7f
                    setTypeface(typeface, Typeface.BOLD)
                    gravity = Gravity.CENTER
                }
            }.lparams(width = dip(45), height = matchParent)

            imageContainer = aspectRatioFrameLayout {
                fixedSide = WIDTH
                aspectRatio = 0.2f

                image = imageView {
                    id = R.id.releaseWallpaper
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }

            }.lparams(width = matchParent)
        }
    }
}