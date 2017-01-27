package com.anko.stinodes.ankoplication.mainactivity.detailfragment.ui

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.DetailFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailFragmentUI: AnkoComponent<DetailFragment> {

    companion object {
        val VIEW_PAGER = R.id.detail_viewpager
    }

    lateinit var titleView: TextView
    lateinit var infoContainer: ViewGroup
    lateinit var episodeRecycler: RecyclerView

    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            verticalLayout {
                verticalLayout {
                    backgroundResource = R.color.red
                    padding = dimen(R.dimen.margin)
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        elevation = 8f

                    titleView = textView {
                        lines = 1
                        textSize = 22f
                        setTypeface(typeface, Typeface.BOLD)
                        textColor = ContextCompat.getColor(context, R.color.white)
                        gravity = Gravity.CENTER
                    }.lparams(width = matchParent)

                    infoContainer = verticalLayout {
                        padding = dimen(R.dimen.margin)
                    }.lparams(width = matchParent) {
                        margin = dimen(R.dimen.margin_small)
                    }

                }.lparams(width = matchParent) {
                    weight = 0f
                }

                episodeRecycler = recyclerView {
                    padding = dimen(R.dimen.margin)
                    clipToPadding = false
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
}