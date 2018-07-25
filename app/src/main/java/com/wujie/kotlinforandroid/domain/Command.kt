package com.wujie.kotlinforandroid.domain

import com.wujie.kotlinforandroid.model.ForecastResult
import java.text.DateFormat
import java.util.*
import com.wujie.kotlinforandroid.model.Forecast as ModelForecast

/**
 * Created by wujie on 2018/7/25/025.
 */

interface Command<T> {
    fun exceute() : T
}
data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)

public class ForecastDataMapper{
    fun covertFromDataModel(forecast: ForecastResult) : ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    fun convertForecastListToDomain(list: List<ModelForecast>) : List<Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(modelForecast: ModelForecast): Forecast {
        return Forecast(converDate(modelForecast.dt), modelForecast.weather[0].description,
                modelForecast.temp.max.toInt(), modelForecast.temp.min.toInt())
    }

    private fun converDate(date: Long) : String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date*1000)
    }
}