package com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import com.anko.stinodes.ankoplication.mainactivity.toolbar.toolbarimagefragment.ui.ToolbarImageFragmentUI
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class ToolbarImageFragment(val args: Bundle): Fragment() {

    val ui = ToolbarImageFragmentUI()
    var imageUrl: String?

    companion object {
        fun create(args: Bundle): Fragment {
            val f = ToolbarImageFragment(args)
            return f
        }
    }

    init {
        imageUrl = args.getString("imageUrl")
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = ui.createView(
                AnkoContext.Companion.create(activity, this)
        )

        if (imageUrl != null)
            setImageIntoView(ui.image, args.getString("imageUrl"))
        return view
    }

    fun setImage(url: String) {
        imageUrl = url
        Log.d("imageview", "Setting image: $url")
        if (imageUrl != null)
            setImageIntoView(ui.image, imageUrl!!)
    }
    fun setImageIntoView(imageView: ImageView, url: String) {
        Picasso.with(activity)
                .load(url)
                .fit()
                .into(
                        imageView,
                        object: Callback {
                            override fun onSuccess() {
                                val anim = AlphaAnimation(0f, 1f)
                                anim.duration = 300
                                anim.fillAfter = true
                                imageView.startAnimation(anim)
                            }
                            override fun onError() {
                                Log.d("imageView", "couldn't load image")
                            }
                        }
                )
    }
}