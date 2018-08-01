package com.wujie.kotlinforandroid.ui.activities

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.domain.commands.RequestDayForecastCommand
import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.extensions.color
import com.wujie.kotlinforandroid.extensions.textColor
import com.wujie.kotlinforandroid.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolBarmanager {
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        const val ID = "DetailActivity:id"
        const val CITY_NAME = "DetailActivity:cityName"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }
        async(UI) {
            val result = bg{ RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()}
            bindForecast(result.await())
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
