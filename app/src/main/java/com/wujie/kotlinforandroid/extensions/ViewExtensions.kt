package com.wujie.kotlinforandroid.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by wujie on 2018/8/1/001.
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}