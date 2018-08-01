package com.wujie.kotlinforandroid.ui.activities

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar

import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.domain.commands.RequestForecastComand
import com.wujie.kotlinforandroid.domain.model.ForecastList
import com.wujie.kotlinforandroid.extensions.DelegatesExt
import com.wujie.kotlinforandroid.ui.adapter.ForecastListAdapter
import org.jetbrains.anko.coroutines.experimental.bg

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity(), ToolBarmanager {
    private val zipCode: Long by DelegatesExt.prefrence(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastComand(zipCode).execute() }
        updateUI(result.await())
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast) {
            startActivity<DetailActivity>(DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to weekForecast.city)
        }
        forecastList.adapter = adapter
        toolbarTitle = "${weekForecast.city} (${weekForecast.country})"
    }
}
