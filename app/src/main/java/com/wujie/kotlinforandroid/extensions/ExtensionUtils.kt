package com.wujie.kotlinforandroid.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by wujie on 2018/8/1/001.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM) : String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}