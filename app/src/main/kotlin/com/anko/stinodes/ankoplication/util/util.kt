package com.anko.stinodes.ankoplication.util

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import java.io.IOException

fun loadJson(context: Context, file: String): String? {
    try {
        val asset = context.assets.open(file)
        val size = asset.available()
        val buffer = ByteArray(size)
        asset.read(buffer)
        asset.close()
        return String(buffer)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}
