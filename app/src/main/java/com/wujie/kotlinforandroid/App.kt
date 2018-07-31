package com.wujie.kotlinforandroid

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by wujie on 2018/7/26/026.
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