package com.wujie.kotlinforandroid

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by wujie on 2018/7/26/026.
 */
class App : Application(){
    companion object {
        private var instance: Application?= null
        fun instance() = instance!!
        val database: SQLiteOpenHelper by lazy {

        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}