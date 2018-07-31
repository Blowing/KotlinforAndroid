package com.wujie.kotlinforandroid.data.db

import com.wujie.kotlinforandroid.model.Forecast
import com.wujie.kotlinforandroid.model.ForecastList

/**
 * Created by wujie on 2018/7/31/031.
 */
class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(id: Long, it: Forecast) = with(it) {
        DayForecast(date, description, high, low, iconUrl, id)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast){
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }
}