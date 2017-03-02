package com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.ext.asRoundedRect
import com.anko.stinodes.ankoplication.ext.aspectRatioFrameLayout
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class ReleaseViewHolderUI(): AnkoComponent<ViewGroup> {

    var onReleaseClicked: ((Release) -> Unit)? = null
    var release: Release? = null
    var image: ImageView? = null
    var title: TextView? = null
    var episode: TextView? = null

    fun bindWallpaperImage(context: Context) {
        if (image != null && release != null)
            Picasso.with(context)
                    .load("${IMAGE_URL}wallpaper/2/${release!!.anime?.wallpaper}")
                    .fit()
                    .asRoundedRect(radius = context.dip(5f).toFloat(), bottomLeft = false, bottomRight = false)
                    .into(image)
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with (ui) {
        verticalLayout {
            lparams(width = matchParent) {
                margin = dip(8)
            }
            backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.dark_card)

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                elevation = 8f

            aspectRatioFrameLayout {
                fixedSide = AspectRatioFrameLayout.Side.WIDTH
                aspectRatio = 0.5f

                onClick {
                    if (release != null && onReleaseClicked != null)
                        onReleaseClicked!!(release!!)
                }

                image = imageView {
                    id = R.id.releaseWallpaper
                }
            }.lparams(width = matchParent)

            verticalLayout {
                padding = dip(10)
                bottomPadding = dip(6)

                title = textView {
                    lines = 1
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    textSize = 12f
                    alpha = 0.9f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams()

                episode = textView {
                    lines = 1
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    textSize = 10f
                    alpha = 0.6f
                    topPadding = dip(4)
                }.lparams()
            }
        }
    }
}