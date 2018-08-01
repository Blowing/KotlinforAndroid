package com.wujie.kotlinforandroid.domain.commands

import com.wujie.kotlinforandroid.domain.datasource.ForecastProvider
import com.wujie.kotlinforandroid.domain.model.ForecastList


/**
 * Created by wujie on 2018/8/1/001.
 */
class RequestForecastComand(private val zipCode: Long,
                            private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList>{

    companion object {
        const val DAYS = 7
    }
    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}