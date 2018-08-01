package com.wujie.kotlinforandroid.domain.commands

import com.wujie.kotlinforandroid.domain.datasource.ForecastProvider
import com.wujie.kotlinforandroid.domain.model.Forecast

/**
 * Created by wujie on 2018/8/1/001.
 */
class RequestDayForecastCommand (val id: Long, private val forecastProvider: ForecastProvider =
        ForecastProvider()) : Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)

}