package com.anko.stinodes.ankoplication.widget

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent

class ToggleableNestScrollView: NestedScrollView {

    var scrollable = true

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attributeSet: AttributeSet)
            : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int)
            :super(context, attributeSet, defStyle)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (scrollable)
            return super.onInterceptTouchEvent(ev)
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (scrollable)
            return super.onTouchEvent(ev)
        return false
    }

}