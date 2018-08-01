package com.wujie.kotlinforandroid.domain.datasource

import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.domain.model.ForecastList

/**
 * Created by wujie on 2018/8/1/001.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date:Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}