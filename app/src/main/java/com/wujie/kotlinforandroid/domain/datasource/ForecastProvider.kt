package com.wujie.kotlinforandroid.domain.datasource

import com.wujie.kotlinforandroid.data.db.ForecastDb
import com.wujie.kotlinforandroid.data.server.ForecastServer
import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.domain.model.ForecastList
import com.wujie.kotlinforandroid.extensions.firstResult

/**
 * Created by wujie on 2018/8/1/001.
 */
class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const  val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }

    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)

    }

    private fun todayTimeSpan() = System.currentTimeMillis()

    private fun <T : Any> requestToSources(f: (ForecastDataSource) ->T?):  T = sources.firstResult { f(it) }
}