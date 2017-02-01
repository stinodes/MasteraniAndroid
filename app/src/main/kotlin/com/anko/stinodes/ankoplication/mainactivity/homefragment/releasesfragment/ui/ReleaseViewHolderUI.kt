package com.anko.stinodes.ankoplication.mainactivity.homefragment.releasesfragment.ui

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
                    .asRoundedRect(2.5f)
                    .into(image)
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with (ui) {
        verticalLayout {
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
                fixedSide = AspectRatioFrameLayout.Side.WIDTH
                aspectRatio = 0.56f

                onClick {
                    if (release != null && onReleaseClicked != null)
                        onReleaseClicked!!(release!!)
                }

                image = imageView {
                    id = R.id.releaseWallpaper
                }
            }.lparams(width = matchParent)

            linearLayout {
                padding = dip(4)
                title = textView {
                    lines = 1
                    textColor = ContextCompat.getColor(context, R.color.white)
                    textSize = 12f
                    alpha = 0.8f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams() {
                    weight = 1f
                }

                episode = textView {
                    lines = 1
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    textSize = 12f
                    alpha = 0.6f
                    setTypeface(typeface, Typeface.BOLD)
                    leftPadding = dip(4)
                }.lparams()
            }
            view {
                backgroundResource = R.color.colorPrimary
                alpha = 0.5f
            }.lparams(width = dip(64), height = dip(1)) {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }
}