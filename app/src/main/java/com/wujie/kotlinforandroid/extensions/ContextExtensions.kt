package com.wujie.kotlinforandroid.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by wujie on 2018/8/1/001.
 */
fun Context.color(res: Int) = ContextCompat.getColor(this, res)