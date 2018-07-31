package com.wujie.kotlinforandroid.ui

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by wujie on 2018/7/31/031.
 */
class App : Application(){

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}