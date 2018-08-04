package com.wujie.kotlinforandroid.utils

import android.app.Activity
import android.os.Build
import android.support.annotation.ColorInt
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by wujie on 2018/8/3/003.
 */
class Utils {

    companion object {
        fun isBeTweenTime(startTime: String, endTime: String) : Boolean {
            var result = false
            val dateFormat = SimpleDateFormat("HH:mm")
            val startDate = dateFormat.parse(startTime)
            val endDate = dateFormat.parse(endTime)
            val nowDate = dateFormat.parse(dateFormat.parse(Date().toString()).toString())
            if ((nowDate.after(startDate) || nowDate == startDate) && (nowDate.before(endDate) || nowDate == endDate)) {
                result = true
            }
            return result
        }



        /**
         * 设置状态栏颜色的公共方法
         */
        fun psetStatusBarBackgroundColor(activity: Activity, @ColorInt colorPref: Int, isLight: Boolean) {

            if(Build.VERSION.SDK_INT >= 23) {
                val window: Window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if(isLight) {
                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
                window.statusBarColor = colorPref
            }

        }
    }
}