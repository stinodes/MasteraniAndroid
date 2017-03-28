package com.anko.stinodes.ankoplication.mainactivity.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.*
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.FrameLayout
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.MainActivity
import com.anko.stinodes.ankoplication.util.HeightAnimation
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import kotlin.properties.Delegates


class MainActivityUI: AnkoComponent<MainActivity> {
    companion object {
        val APP_BAR_ID = R.id.app_bar
        val FRAGMENT_CONT_ID = R.id.fragment_container
    }

    lateinit var contentContainer: FrameLayout
    lateinit var appBar: AppBarLayout
    lateinit var tabs: TabLayout

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