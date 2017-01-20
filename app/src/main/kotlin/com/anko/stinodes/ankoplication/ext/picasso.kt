package com.anko.stinodes.ankoplication.ext

import android.graphics.*
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation




fun RequestCreator.asRoundedRect(radius: Float, margin: Float = 0f): RequestCreator {
    transform(
            object : Transformation {
                override fun transform(source: Bitmap): Bitmap {
                    val paint = Paint()
                    paint.isAntiAlias = true
                    paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

                    val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(output)
                    canvas.drawRoundRect(
                            RectF(
                                    margin,
                                    margin,
                                    (source.width - margin).toFloat(),
                                    (source.height - margin).toFloat()
                            ), radius, radius, paint)

                    if (source !== output) {
                        source.recycle()
                    }

                    return output
                }

                override fun key(): String {
                    return "rounded rect"
                }
            }
    )
    return this
}
