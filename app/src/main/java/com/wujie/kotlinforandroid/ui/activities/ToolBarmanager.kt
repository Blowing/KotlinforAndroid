package com.wujie.kotlinforandroid.ui.activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.extensions.slideEnter
import com.wujie.kotlinforandroid.extensions.slideExit
import com.wujie.kotlinforandroid.ui.App
import com.wujie.kotlinforandroid.utils.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by wujie on 2018/8/1/001.
 */
interface ToolBarmanager {
    val toolbar: Toolbar

    var toolbarTitle: String
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        get() = toolbar.title.toString()
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        set(value) {toolbar.title = value}

    @SuppressLint("NewApi")
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else -> App.instance.toast("nihao")
            }
            true
        }
    }

    @SuppressLint("NewApi")
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}