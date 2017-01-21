package com.anko.stinodes.ankoplication.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class HeightAnimation(
        val view: View,
        val to: Int,
        val d: Long
): Animation() {

    var from: Int = view.measuredHeight
    var delta: Int = to - from

    init {
        duration = d
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height =
                if (interpolatedTime == 1f) to
                else Math.round(from + delta * interpolatedTime)
        view.requestLayout()
    }

}