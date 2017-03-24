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
    var ratingContainer: ViewGroup? = null
    var year: TextView? = null
    var title: TextView? = null

    fun bindWallpaperImage(context: Context) {
        if (image != null && anime != null && anime!!.poster != null)
            Picasso.with(context)
                    .load("${IMAGE_URL}poster/${anime?.poster?.file}")
                    .fit()
                    .asRoundedRect(radius = context.dip(5f).toFloat(), bottomLeft = false, bottomRight = false)
                    .into(image)
    }

    fun bindRating(score: Float) {
        with(ratingContainer!!) {
            linearLayout {
                var i = 1
                val mod = score % 1
                val starSize = 9

                while (i < score) {

                    imageView {
                        imageResource = R.drawable.star
                    }.lparams(height = dip(starSize), width = dip(starSize)) {
                        setMargins(0, 0, 2, 0)
                        gravity = Gravity.CENTER_VERTICAL or Gravity.END
                    }

                    i++
                }
                if (mod > 0) {
                    frameLayout {
                        imageView {
                            imageResource = R.drawable.star
                        }.lparams(height = dip(starSize), width = dip(starSize)) {
                        }
                    }.lparams(width = dip(starSize * mod)) {
                        gravity = Gravity.CENTER_VERTICAL or Gravity.END
                        setMargins(0, 0, 2, 0)
                    }
                }
            }
        }
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with (ui) {
        verticalLayout {
            lparams(width = matchParent) {
                margin = dip(4)
            }
            backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.dark_card)

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                elevation = 8f

            aspectRatioFrameLayout {
                onClick { if (onAnimeClicked != null && anime != null) onAnimeClicked?.invoke(anime!!) }
                fixedSide = AspectRatioFrameLayout.Side.WIDTH
                aspectRatio = 1.3f

                image = imageView {
                    id = R.id.releaseWallpaper
                }
            }.lparams(width = matchParent)

            verticalLayout {
                padding = dip(4)

                linearLayout {
                    topPadding = dip(2)

                    frameLayout {
                        year = textView {
                            textSize = 10f
                            textColor = ContextCompat.getColor(context, R.color.white2)
                            alpha = 0.6f
                            lines = 1
                        }.lparams()
                    }.lparams() {
                        weight = 1f
                    }

                    ratingContainer = frameLayout {
                        topPadding = dip(4)
                        gravity = Gravity.RIGHT or Gravity.END
                    }.lparams()
                }

                title = textView {
                    topPadding = dip(2)
                    bottomPadding = dip(4)
                    textSize = 13f
                    textColor = ContextCompat.getColor(context, R.color.white2)
                    alpha = 0.9f
                    lines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    setTypeface(typeface, Typeface.BOLD)
                }
            }
        }
    }
}