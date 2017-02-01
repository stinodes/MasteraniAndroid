package com.anko.stinodes.ankoplication.mainactivity.homefragment.animefragment.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.ext.asRoundedRect
import com.anko.stinodes.ankoplication.ext.aspectRatioFrameLayout
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class AnimeViewHolderUI(): AnkoComponent<ViewGroup> {

    var onAnimeClicked: ((Anime) -> Unit)? = null
    var anime: Anime? = null
    var image: ImageView? = null
    var title: TextView? = null

    fun bindWallpaperImage(context: Context) {
        if (image != null && anime != null && anime!!.poster != null)
            Picasso.with(context)
                    .load("${IMAGE_URL}poster/${anime?.poster?.file}")
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
                aspectRatio = 1.42f

                image = imageView {
                    id = R.id.releaseWallpaper
                }
            }.lparams(width = matchParent)

            linearLayout {
                padding = dip(4)
                backgroundResource = R.drawable.overlay_transparent_gradient
                title = textView {
                    textSize = 12f
                    textColor = ContextCompat.getColor(context, R.color.white)
                    alpha = 0.8f
                    lines = 2
                    ellipsize = TextUtils.TruncateAt.END
                    setShadowLayer(3f, 0f, 2f, ContextCompat.getColor(context, R.color.black))
                    setTypeface(typeface, Typeface.BOLD)
                }
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