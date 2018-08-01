package com.wujie.kotlinforandroid.domain.commands

/**
 * Created by wujie on 2018/8/1/001.
 */
interface Command<out T> {
    fun execute(): T
}