package com.anko.stinodes.ankoplication.ext

import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import android.widget.FrameLayout
import com.anko.stinodes.ankoplication.widget.AspectRatioFrameLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.wrapContent

fun collapseMode(mode: Int): android.support.design.widget.CollapsingToolbarLayout.LayoutParams.() -> Unit = { collapseMode = mode }

fun Context.attr(@AttrRes attribute: Int): TypedValue {
    var typed = TypedValue()
    ctx.theme.resolveAttribute(attribute, typed, true)
    return typed
}

//returns px
fun Context.dimenAttr(@AttrRes attribute: Int): Int = TypedValue.complexToDimensionPixelSize(attr(attribute).data, resources.displayMetrics)

//returns color
fun Context.colorAttr(@AttrRes attribute: Int): Int {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        resources.getColor(attr(attribute).resourceId, ctx.theme)
    } else {
        @Suppress("DEPRECATION")
        resources.getColor(attr(attribute).resourceId)
    }
}

fun AnkoContext<*>.dimenAttr(@AttrRes attribute: Int): Int = ctx.dimenAttr(attribute)
fun AnkoContext<*>.colorAttr(@AttrRes attribute: Int): Int = ctx.colorAttr(attribute)
fun AnkoContext<*>.attribute(@AttrRes attribute: Int): TypedValue = ctx.attr(attribute)

fun View.dimenAttr(@AttrRes attribute: Int): Int = context.dimenAttr(attribute)
fun View.colorAttr(@AttrRes attribute: Int): Int = context.colorAttr(attribute)
fun View.attr(@AttrRes attribute: Int): TypedValue = context.attr(attribute)

fun Fragment.dimenAttr(@AttrRes attribute: Int): Int = activity.dimenAttr(attribute)
fun Fragment.colorAttr(@AttrRes attribute: Int): Int = activity.colorAttr(attribute)
fun Fragment.attr(@AttrRes attribute: Int): TypedValue = activity.attr(attribute)

fun ViewManager.aspectRatioFrameLayout() = aspectRatioFrameLayout{}
fun ViewManager.aspectRatioFrameLayout(init: AspectRatioFrameLayout.() -> Unit) =
        ankoView(::AspectRatioFrameLayout, init)


object FrameLayout {
    fun <T : View> T.lparams(
            width: kotlin.Int = wrapContent, height: kotlin.Int = wrapContent,
            init: FrameLayout.LayoutParams.() -> kotlin.Unit = {}): T {
        val layoutParams = android.widget.FrameLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}

object CollapsingToolbar {
    fun <T : View> T.lparams(
            width: kotlin.Int = wrapContent, height: kotlin.Int = wrapContent,
            init: CollapsingToolbarLayout.LayoutParams.() -> kotlin.Unit = {}): T {
        val layoutParams = CollapsingToolbarLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}
