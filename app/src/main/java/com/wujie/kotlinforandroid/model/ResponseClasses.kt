package com.wujie.kotlinforandroid.model

/**
 * Created by wujie on 2018/7/24/024.
 * 数据类
 */

public data class ForecastResult(val city: City, val list: List<Forecast>)

public data class City(val id: Long, val name: String, val coord: Coordinates, val country: String,
                val population: Int)

public data class Coordinates(val lon: Float, val lat: Float)

public data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float, val humidity:
Int,
                    val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int,
                    val rain: Float)

public data class Temperature(val day: Float, val min: Float, val max: Float, val night: Float,
                       val eve: Float, val morn: Float)

public data class Weather(val id: Long, val main: String, val description: String, val icon: String)