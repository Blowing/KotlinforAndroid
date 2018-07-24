package com.wujie.kotlinforandroid.utils

import android.util.Log
import java.net.URL

/**
 * Created by wujie on 2018/7/24/024.
 *
 */
 class Request(val url: String) {
     fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr )
    }
}