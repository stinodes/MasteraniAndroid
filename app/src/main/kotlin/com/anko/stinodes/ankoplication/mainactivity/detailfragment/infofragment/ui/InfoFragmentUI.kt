package com.anko.stinodes.ankoplication.mainactivity.detailfragment.infofragment.ui

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anko.stinodes.ankoplication.R
import com.anko.stinodes.ankoplication.mainactivity.detailfragment.episodesfragment.InfoFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class InfoFragmentUI: AnkoComponent<InfoFragment> {

    lateinit var titleView: TextView
    lateinit var infoContainer: ViewGroup

    override fun createView(ui: AnkoContext<InfoFragment>): View = with(ui) {
        nestedScrollView {
            lparams(width = matchParent, height = matchParent)

            verticalLayout {
                titleView = textView {
                    lines = 1
                    textSize = 22f
                    setTypeface(typeface, Typeface.BOLD)
                    textColor = ContextCompat.getColor(context, R.color.white)
                    gravity = Gravity.CENTER
                }.lparams(width = matchParent)

                infoContainer = verticalLayout {
                }.lparams(width = matchParent)

            }.lparams(width = matchParent) {
                margin = dimen(R.dimen.margin)
            }
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
                textSize = 16f
                setTypeface(typeface, Typeface.NORMAL)
                gravity = Gravity.END or Gravity.RIGHT
                text = value
            }.lparams() {
                weight = 1f
            }
        }

    }
}