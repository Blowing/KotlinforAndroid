package com.wujie.kotlinforandroid.request

import com.wujie.kotlinforandroid.domain.Command
import com.wujie.kotlinforandroid.domain.ForecastDataMapper
import com.wujie.kotlinforandroid.domain.ForecastList

/**
 * Created by wujie on 2018/7/25/025.
 *
 */
class RequestForecastCommand (val zipCode: String) : Command<ForecastList>{
    override fun exceute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().covertFromDataModel(forecastRequest.execute())
    }

}