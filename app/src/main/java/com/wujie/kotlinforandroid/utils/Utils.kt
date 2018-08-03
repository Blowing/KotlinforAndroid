package com.wujie.kotlinforandroid.utils

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
    }
}