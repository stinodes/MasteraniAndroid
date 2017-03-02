package com.anko.stinodes.ankoplication.ext

import android.graphics.*
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation




fun RequestCreator.asRoundedRect(
        radius: Float,
        topLeft: Boolean = true, topRight: Boolean = true,
        bottomLeft: Boolean = true, bottomRight: Boolean = true,
        margin: Float = 0f
): RequestCreator {
    transform(
            object : Transformation {
                override fun transform(source: Bitmap): Bitmap {
                    val w = source.width
                    val h = source.height
                    val output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(output)

                    val color = 0xff424242.toInt()
                    val paint = Paint()
                    val rect = Rect(0, 0, w, h)
                    val rectF = RectF(rect)

                    paint.isAntiAlias = true
                    canvas.drawARGB(0, 0, 0, 0)
                    paint.color = color
                    canvas.drawRoundRect(rectF, radius, radius, paint)

                    //draw rectangles over the corners we want to be square
                    if (!topLeft) {
                        canvas.drawRect(0f, 0f, w / 2f, h / 2f, paint)
                    }
                    if (!topRight) {
                        canvas.drawRect(w / 2f, 0f, w / 1f, h / 2f, paint)
                    }
                    if (!bottomLeft) {
                        canvas.drawRect(0f, h / 2f, w / 2f, h / 1f, paint)
                    }
                    if (!bottomRight) {
                        canvas.drawRect(w / 2f, h / 2f, w / 1f, h / 1f, paint)
                    }

                    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                    canvas.drawBitmap(source, 0f, 0f, paint)

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

fun RequestCreator.asRoundedRect(radius: Float, margin: Float = 0f): RequestCreator
        = this.asRoundedRect(radius, true, true, true, true, margin)
