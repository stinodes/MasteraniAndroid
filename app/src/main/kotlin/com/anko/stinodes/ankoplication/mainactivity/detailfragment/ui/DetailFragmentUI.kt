package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.domain.Genre
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnime
import com.anko.stinodes.ankoplication.ext.asRoundedRect
import com.anko.stinodes.ankoplication.ext.aspectRatioFrameLayout
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import com.anko.stinodes.ankoplication.util.HeightAnimation
import com.anko.stinodes.ankoplication.web.IMAGE_URL
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout.Side.WIDTH
import com.anko.stinodes.ankoplication.widget.ToggleableNestScrollView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailFragmentUI: AnkoComponent<DetailFragment> {

    companion object {
        val COLLAPSED_HEIGHT = 1
        val EXPANDED_HEIGHT = 200
    }

    lateinit var infoCard: ViewGroup
    lateinit var infoCardImage: ImageView

    lateinit var infoHeading: ViewGroup
    lateinit var title: TextView
    lateinit var expandButton: Button

    lateinit var infoDetailsContainer: ViewGroup
    lateinit var infoDetails: ViewGroup

    lateinit var rating: LinearLayout
    lateinit var releaseYear: TextView
    lateinit var episodes: TextView
    lateinit var genres: ViewGroup
    lateinit var description: TextView

    lateinit var parentView: ViewGroup
    lateinit var titleView: TextView
    lateinit var infoContainer: ViewGroup
    lateinit var extendedContainer: ViewGroup
    lateinit var contentContainer: ViewGroup
    lateinit var extendedGradientView: View
    lateinit var scrollingContainer: ToggleableNestScrollView
    lateinit var episodeRecycler: RecyclerView
    lateinit var emptyMessage: ViewGroup

    val episodeScrollListener: OnScrollListener = object: OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
//            if (newState == SCROLL_STATE_DRAGGING)
//                collapseInfo()
        }
    }

    fun bindCardImage(url: String, context: Context) {
        Picasso.with(context)
                .load("${IMAGE_URL}wallpaper/2/$url")
                .fit()
                .asRoundedRect(10f, bottomLeft = false, bottomRight = false)
                .into(infoCardImage)
    }

    fun bindInfo(anime: DetailedAnime) {
        title.text = anime.info!!.title
        releaseYear.text = anime.info!!.dateToString()
        episodes.text = anime.info!!.episodesToString()
        description.text = anime.info!!.synopsis
        bindGenres(anime.genres!!)
        bindRating(anime.info!!.score!!)
    }
    fun bindGenres(genresList: List<Genre>) {
        with(genres) {
            genresList.forEach {
                frameLayout {
                    backgroundResource = R.drawable.primary_gradient_border_pill
                    topPadding = dip(3.5f)
                    bottomPadding = dip(3.5f)
                    leftPadding = dip(6.5f)
                    rightPadding = dip(6.5f)

                    textView {
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            letterSpacing = .02f
                        text = it.name
                        textSize = 11f
                        textColor = ContextCompat.getColor(context, R.color.white)

                    }
                }.layoutParams = with(LinearLayout.LayoutParams(wrapContent, wrapContent)) {
                    leftMargin = dip(3)
                    rightMargin = dip(3)
                    this
                }
            }
        }
    }
    fun bindRating(score: Float) {
        with(rating) {
            linearLayout {
                var i = 1
                val mod = score % 1
                val starSize = 14
                gravity = Gravity.RIGHT or Gravity.END

                while (i < score) {

                    imageView {
                        imageResource = com.anko.stinodes.ankoplication.R.drawable.star
                    }.lparams(height = dip(starSize), width = dip(starSize)) {
                        setMargins(0, 0, 2, 0)
                        gravity = android.view.Gravity.CENTER_VERTICAL or android.view.Gravity.END
                    }

                    i++
                }
                if (mod > 0) {
                    frameLayout {
                        imageView {
                            imageResource = com.anko.stinodes.ankoplication.R.drawable.star
                        }.lparams(height = dip(starSize), width = dip(starSize)) {
                        }
                    }.lparams(width = dip(starSize * mod)) {
                        gravity = android.view.Gravity.CENTER_VERTICAL or android.view.Gravity.END
                        setMargins(0, 0, 2, 0)
                    }
                }
            }
        }
    }

    fun expandInfo() {
        infoDetails.measure(View.MeasureSpec.makeMeasureSpec(infoDetailsContainer.measuredWidth, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        infoDetailsContainer.startAnimation(
                HeightAnimation(
                        infoDetailsContainer,
                        Math.min(
                                infoDetailsContainer.dip(EXPANDED_HEIGHT),
                                infoDetails.measuredHeight
                        ),
                        300
                )
        )
        expandButton.onClick { collapseInfo() }
//        scrollingContainer.scrollable = true
//        episodeRecycler.addOnScrollListener(episodeScrollListener)
    }
    fun collapseInfo() {
        infoDetailsContainer.startAnimation(
                HeightAnimation(
                        infoDetailsContainer,
                        COLLAPSED_HEIGHT,
                        300
                )
        )
        expandButton.onClick { expandInfo() }
//        episodeRecycler.removeOnScrollListener(episodeScrollListener)
    }

    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            parentView = verticalLayout {

                // CARD
                infoCard = verticalLayout {
                    backgroundResource = R.drawable.primary_filled_roundedrect
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        elevation = 8f

                    // IMAGE
                    frameLayout {
                        aspectRatioFrameLayout {
                            aspectRatio = .57f
                            fixedSide = WIDTH

                            infoCardImage = imageView {

                            }.lparams(width = matchParent, height = matchParent)

                        }.lparams(width = matchParent)
                    }.lparams(width = matchParent)

                    // INFO
                    verticalLayout {

                        infoHeading = linearLayout {
                            topPadding = dip(8)
                            bottomPadding = dip(8)
                            leftPadding = dip(24)
                            rightPadding = dip(24)

                            gravity = Gravity.CENTER_VERTICAL

                            title = textView {
                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                    letterSpacing = .015f
                                maxLines = 2
                                textColor = ContextCompat.getColor(context, R.color.white)
                                textSize = 18f
                                setTypeface(typeface, Typeface.BOLD)
                                alpha = 0.9f
                            }.lparams(width = matchParent) {
                                weight = 1f
                            }

                            expandButton = button {
                                backgroundColor = ContextCompat.getColor(context, R.color.transparent)
                                text = "More"
                                textSize = 15f
                                textColor = ContextCompat.getColor(context, R.color.white)
                                alpha = 0.6f
                                setTypeface(typeface, Typeface.BOLD)
                                gravity = Gravity.RIGHT or Gravity.END or Gravity.CENTER_VERTICAL

                                onClick { expandInfo() }
                            }.lparams() {
                                gravity = Gravity.RIGHT or Gravity.END or Gravity.CENTER_VERTICAL
                            }

                        }.lparams(width = matchParent)


                        //Container
                        infoDetailsContainer = scrollView {

                            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                                scrollBarSize = dip(1)

                            infoDetails = verticalLayout {

                                leftPadding = dip(40)
                                rightPadding = dip(40)

                                // Rating
                                linearLayout {

                                    bottomPadding = dip(4)

                                    textView {

                                        text = "Score"
                                        infoTextStyling(this, true)

                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                    }

                                    rating = linearLayout {
                                        gravity = Gravity.RIGHT or Gravity.END
                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                        gravity = Gravity.RIGHT or Gravity.END
                                    }

                                }.lparams(width = matchParent)

                                //Year
                                linearLayout {

                                    topPadding = dip(4)
                                    bottomPadding = dip(4)

                                    textView {

                                        text = "Airing Year"
                                        infoTextStyling(this, true)

                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                    }
                                    releaseYear = textView {

                                        text = "-"
                                        infoTextStyling(this, false)

                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                        gravity = Gravity.RIGHT or Gravity.END
                                    }

                                }.lparams(width = matchParent)

                                //Episodes
                                linearLayout {

                                    topPadding = dip(4)
                                    bottomPadding = dip(4)

                                    textView {

                                        text = "Episodes"
                                        infoTextStyling(this, true)

                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                    }
                                    episodes = textView {

                                        text = "-"
                                        infoTextStyling(this, false)

                                    }.lparams(width = matchParent) {
                                        weight = 1f
                                        gravity = Gravity.RIGHT or Gravity.END
                                    }

                                }.lparams(width = matchParent)

                                //Tags
                                horizontalScrollView {
                                    isHorizontalScrollBarEnabled = false
                                    genres = linearLayout {
                                        topPadding = dip(4f)
                                    }
                                }.lparams(width = matchParent)

                                //Description
                                frameLayout {
                                    topPadding = dip(8)
                                    bottomPadding = dip(20)
                                    description = textView {
                                        textColor = ContextCompat.getColor(context, R.color.white)
                                        infoTextStyling(this, true)
                                        alpha = .9f
                                    }
                                }.lparams(width = matchParent, height = matchParent) {
                                    weight = 1f
                                }

                            }.lparams(width = matchParent, height = wrapContent)

                        }.lparams( width = matchParent, height = dip(COLLAPSED_HEIGHT))

                    }.lparams(width = matchParent)


                }.lparams(width = matchParent) {
                    margin = dip(10)
                    bottomMargin = 0
                }

                episodeRecycler = recyclerView {
                    padding = dimen(R.dimen.margin)
                    clipToPadding = false
                }.lparams(width = matchParent) {
                    weight = 1f
                }

                emptyMessage = verticalLayout {
                    padding = dip(32)

                    textView {
                        text = "There are no episodes to show."
                        textColor = ContextCompat.getColor(context, R.color.white)
                        textSize = 18f
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                }.lparams(width = matchParent) {
                    weight = 1f
                }

            }.lparams(width = matchParent, height = matchParent)
        }
    }

    fun infoTextStyling(view: TextView, isLabel: Boolean) = with(view) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            letterSpacing = .03f

        if (isLabel) {
            alpha = 0.7f
        }
        else {
            gravity = Gravity.RIGHT or Gravity.END
            alpha = 0.9f
        }

        textSize = 14f
        textColor = ContextCompat.getColor(context, R.color.white2)
    }
}