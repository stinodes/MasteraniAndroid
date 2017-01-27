package com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ui

import android.view.View
import android.widget.ImageView
import com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ToolbarImageFragment
import org.jetbrains.anko.*

class ToolbarImageFragmentUI: AnkoComponent<ToolbarImageFragment>{

    lateinit var image: ImageView

    override fun createView(ui: AnkoContext<ToolbarImageFragment>): View = with(ui) {
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            image = imageView {}.lparams(width = matchParent, height = matchParent)
        }
    }
}