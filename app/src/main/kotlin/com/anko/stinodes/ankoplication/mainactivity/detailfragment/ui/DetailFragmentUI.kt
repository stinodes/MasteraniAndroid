package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.ext.toggleableNestScrollView
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import com.anko.stinodes.ankoplication.util.HeightAnimation
import com.anko.stinodes.ankoplication.widget.ToggleableNestScrollView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailFragmentUI: AnkoComponent<DetailFragment> {

    companion object {
        val COLLAPSED_HEIGHT = 180
        val EXPANDED_HEIGHT = 350
    }

    lateinit var parentView: ViewGroup
    lateinit var titleView: TextView
    lateinit var infoContainer: ViewGroup
    lateinit var description: TextView
    lateinit var extendedContainer: ViewGroup
    lateinit var contentContainer: ViewGroup
    lateinit var extendedGradientView: View
    lateinit var expandButton: ImageButton
    lateinit var scrollingContainer: ToggleableNestScrollView
    lateinit var episodeRecycler: RecyclerView
    lateinit var emptyMessage: ViewGroup

    val episodeScrollListener: OnScrollListener = object: OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == SCROLL_STATE_DRAGGING)
                collapseInfo()
        }
    }

    fun expandInfo() {
        extendedContainer.startAnimation(
                HeightAnimation(
                        extendedContainer,
                        Math.min(
                                extendedContainer.dip(EXPANDED_HEIGHT),
                                contentContainer.measuredHeight
                        ),
                        300
                )
        )
        scrollingContainer.scrollable = true
        expandButton.onClick { collapseInfo() }
        episodeRecycler.addOnScrollListener(episodeScrollListener)
    }
    fun collapseInfo() {
        extendedContainer.startAnimation(
                HeightAnimation(
                        extendedContainer,
                        extendedContainer.dip(COLLAPSED_HEIGHT),
                        300
                )
        )
        scrollingContainer.smoothScrollTo(0, 0)
        scrollingContainer.scrollable = false
        expandButton.onClick { expandInfo() }
        episodeRecycler.removeOnScrollListener(episodeScrollListener)
    }

    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            parentView = verticalLayout {

                extendedContainer = relativeLayout {
                    backgroundResource = R.color.red
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        elevation = 8f

                    scrollingContainer = toggleableNestScrollView {
                        scrollable = false

                        contentContainer = verticalLayout {
                            padding = dimen(R.dimen.margin)
                            bottomPadding = dip(80)

                            titleView = textView {
                                lines = 1
                                textSize = 22f
                                setTypeface(typeface, Typeface.BOLD)
                                textColor = ContextCompat.getColor(context, R.color.white)
                                gravity = Gravity.CENTER
                            }.lparams(width = matchParent)

                            infoContainer = verticalLayout {
                                leftPadding = dimen(R.dimen.margin)
                                rightPadding = dimen(R.dimen.margin)
                            }.lparams(width = matchParent) {
                                leftMargin = dimen(R.dimen.margin_small)
                                rightMargin = dimen(R.dimen.margin_small)
                            }

                            verticalLayout {
                                rightPadding = dimen(R.dimen.margin)
                                leftPadding = dimen(R.dimen.margin)

                                description = textView {
                                    topPadding = dimen(R.dimen.margin_small)
                                    textSize = 13f
                                    textColor = ContextCompat.getColor(context, R.color.white2)
                                    gravity = Gravity.FILL_HORIZONTAL
                                }.lparams(width = matchParent)

                            }.lparams(width = matchParent) {
                                rightMargin = dimen(R.dimen.margin_small)
                                leftMargin = dimen(R.dimen.margin_small)
                            }
                        }
                    }.lparams(width = matchParent, height = matchParent) {
                        alignWithParent = true
                    }

                    extendedGradientView = frameLayout {
                        backgroundResource = R.drawable.primary_transparent_gradient

                        onClick { expandButton.performClick() }

                        expandButton = imageButton {
                            backgroundResource = R.drawable.design_fab_background

                            onClick { expandInfo() }
                        }.lparams(height = dip(32), width = dip(32)) {
                            gravity = Gravity.CENTER
                        }

                    }.lparams(width = matchParent, height = dip(64)) {
                        alignParentBottom()
                    }

                }.lparams(width = matchParent, height = dip(180))

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

    fun infoField(viewgroup: ViewGroup, key: String, value: String) = with(viewgroup) {
        linearLayout {
            lparams(width = matchParent) {
                topMargin = dimen(R.dimen.margin_small)
                bottomMargin = dimen(R.dimen.margin_small)
            }
            textView {
                lines = 1
                textColor = ContextCompat.getColor(context, R.color.white2)
                alpha = 0.7f
                textSize = 14f
                setTypeface(typeface, Typeface.NORMAL)
                gravity = Gravity.START or Gravity.LEFT
                text = key
            }.lparams {
                weight = 1f
            }
            textView {
                lines = 1
                textColor = ContextCompat.getColor(context, R.color.white2)
                alpha = 0.9f
                textSize = 14f
                setTypeface(typeface, Typeface.NORMAL)
                gravity = Gravity.END or Gravity.RIGHT
                text = value
            }.lparams() {
                weight = 1f
            }
        }
    }
    fun infoField(viewgroup: ViewGroup, key: String, value: (ViewGroup) -> ViewGroup) = with(viewgroup) {
        linearLayout {
            lparams(width = matchParent) {
                topMargin = dimen(R.dimen.margin_small)
                bottomMargin = dimen(R.dimen.margin_small)
            }
            textView {
                lines = 1
                textColor = ContextCompat.getColor(context, R.color.white2)
                alpha = 0.7f
                textSize = 14f
                setTypeface(typeface, Typeface.NORMAL)
                gravity = Gravity.START or Gravity.LEFT
                text = key
            }.lparams {
                weight = 1f
            }
            relativeLayout {
                gravity = Gravity.END or Gravity.RIGHT
                value(this@relativeLayout)
            }.lparams() {
                weight = 1f
            }
        }
    }
}