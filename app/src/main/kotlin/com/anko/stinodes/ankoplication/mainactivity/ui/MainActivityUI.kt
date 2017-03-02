package com.anko.stinodes.ankoplication.mainactivity.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.ext.CollapsingToolbar.lparams
import com.anko.stinodes.ankoplication.ext.aspectRatioFrameLayout
import com.anko.stinodes.ankoplication.ext.collapseMode
import com.anko.stinodes.ankoplication.ext.dimenAttr
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.util.HeightAnimation
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout.Side.WIDTH
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import kotlin.properties.Delegates


class MainActivityUI: AnkoComponent<MainActivity> {
    companion object {
        val APP_BAR_ID = R.id.app_bar
        val TOOLBAR_ID = R.id.toolbar
        val FRAGMENT_CONT_ID = R.id.fragment_container
    }

    lateinit var contentContainer: FrameLayout
    lateinit var toolbar: Toolbar
    lateinit var appBar: AppBarLayout
    lateinit var appBarBackground: ImageView
    lateinit var appBarImage: ImageView
    lateinit var toolbarFragmentContainer: FrameLayout
    lateinit var tabs: TabLayout
    var appBarImageVisible = false

    var toolbarHeight: Int by Delegates.notNull()

    val randomImageUrl: String
        get() {
            val images = listOf<String>(
                    "https://s-media-cache-ak0.pinimg.com/originals/11/c6/0d/11c60d1baacff6e76068236b2324e420.jpg",
                    "https://s-media-cache-ak0.pinimg.com/originals/7c/43/6d/7c436db6400b75577f7c9ab7a6968eee.jpg",
                    "https://s-media-cache-ak0.pinimg.com/originals/96/7e/5a/967e5a1e0d7078684ffc3fd1e4e64811.jpg",
                    "https://zinian.files.wordpress.com/2013/05/35740901.jpg"
            )
            val index = Math.floor(Math.random() * images.size).toInt()
            return images[index]
        }

    fun collapseTabs() {
        tabs.startAnimation(
                HeightAnimation(
                        tabs,
                        0,
                        300
                )
        )
    }
    fun expandTabs() {
        tabs.startAnimation(
                HeightAnimation(
                        tabs,
                        toolbarHeight,
                        300
                )
        )
    }

    override fun createView(ui: AnkoContext<MainActivity>): View  = with(ui) {
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            toolbarHeight = dimen(R.dimen.tool_bar_height)

            appBar = appBarLayout {
                id = APP_BAR_ID
                backgroundResource = R.drawable.primary_background_gradient
                elevation = 8f

                collapsingToolbarLayout {
                    setCollapsedTitleTextAppearance(R.style.Toolbar_title)
                    setExpandedTitleTextAppearance(R.style.Toolbar_title_expanded)

                    toolbar = toolbar {
                        id = TOOLBAR_ID
                        setTitleTextAppearance(context, R.style.Toolbar_title)

                    }.lparams(
                            width = matchParent,
                            height = dimenAttr(R.attr.actionBarSize),
                            init = collapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN)
                    )

                    aspectRatioFrameLayout {
                        aspectRatio = 0.56f
                        fixedSide = WIDTH

                        appBarBackground = imageView {
                            Picasso.with(context)
                                    .load(randomImageUrl)
                                    .fit()
                                    .into(this)

                        }
                    }.lparams(
                            width = matchParent,
                            init = collapseMode(COLLAPSE_MODE_PARALLAX)
                    )

                    toolbarFragmentContainer = frameLayout {
                        id = R.id.toolbarViewContainer

                    }.lparams(
                            width = matchParent,
                            height = matchParent,
                            init = collapseMode(COLLAPSE_MODE_PARALLAX)
                    )
                }.lparams(width = matchParent/*, height = dip(250)*/) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_SNAP or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

                tabs = tabLayout {
                    setSelectedTabIndicatorHeight(dip(4))
                    setSelectedTabIndicatorColor(
                            ContextCompat.getColor(context, R.color.colorPrimaryDark)
                    )
                    setTabTextColors(
                            ContextCompat.getColor(context, R.color.white2),
                            ContextCompat.getColor(context, R.color.white)
                    )
                    backgroundResource = R.drawable.primary_background_gradient
                }.lparams(width = matchParent, height = 0) {
                    scrollFlags = SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
            }.lparams(width = matchParent) {}

            contentContainer = frameLayout {
                id = FRAGMENT_CONT_ID
                backgroundResource = R.drawable.dark_background_gradient
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}