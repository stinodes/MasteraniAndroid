package com.anko.stinodes.ankoplication.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.anko.stinodes.ankoplication.R

class AspectRatioFrameLayout: FrameLayout {
    var aspectRatio: Float? = null
    var fixedSide: Side? = null

    enum class Side(val side: Int) {
        WIDTH(0),
        HEIGHT(1)
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioFrameLayout)

        aspectRatio = a.getFloat(R.styleable.AspectRatioFrameLayout_aspectRatio, 1f)

        when(a.getInt(R.styleable.AspectRatioFrameLayout_fixedSide, 0)) {
            0 -> fixedSide = Side.WIDTH
            1 -> fixedSide = Side.HEIGHT
        }

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (aspectRatio == null)
            return super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        when(fixedSide) {
            Side.WIDTH ->
                super.onMeasure(
                        widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(
                                Math.round(MeasureSpec.getSize(widthMeasureSpec) * aspectRatio!!),
                                MeasureSpec.EXACTLY
                        )
                )
            Side.HEIGHT ->
                super.onMeasure(
                        MeasureSpec.makeMeasureSpec(
                                Math.round(MeasureSpec.getSize(heightMeasureSpec) * aspectRatio!!),
                                MeasureSpec.EXACTLY
                        ),
                        heightMeasureSpec
                )
        }
    }
}