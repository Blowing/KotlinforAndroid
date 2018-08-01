package com.wujie.kotlinforandroid.data.server

import com.wujie.kotlinforandroid.data.db.ForecastDb
import com.wujie.kotlinforandroid.domain.datasource.ForecastDataSource
import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.domain.model.ForecastList

/**
 * Created by wujie on 2018/8/1/001.
 *
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?{
        val result = ForecastByZipCodeRequest(zipCode).excute()
        val converted = dataMapper.convertToDomain(zipCode,result)
        forecastDb.saveForecast(converted)
        return  forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()

}