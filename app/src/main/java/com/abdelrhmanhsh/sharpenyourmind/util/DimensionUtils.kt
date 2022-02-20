package com.abdelrhmanhsh.sharpenyourmind.util

import android.content.res.Resources
import android.graphics.Rect
import android.graphics.RectF
import android.util.DisplayMetrics

private val displayMetrics: DisplayMetrics by lazy {
    Resources.getSystem().displayMetrics
}

// Returns boundary of the screen in pixels (px).
val screenRectPx: Rect get() = displayMetrics.run { Rect(0, 0, widthPixels, heightPixels) }

// Returns boundary of the screen in density independent pixels (dp).
val screenRectDp: RectF get() = screenRectPx.run { RectF(0f, 0f, right.px2dp, bottom.px2dp) }

// Converts any given number from pixels (px) into density independent pixels (dp).
val Number.px2dp: Float get() = this.toFloat() / displayMetrics.density