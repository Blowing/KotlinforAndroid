package com.wujie.kotlinforandroid.ui.activities

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.domain.commands.RequestDayForecastCommand
import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.extensions.color
import com.wujie.kotlinforandroid.extensions.textColor
import com.wujie.kotlinforandroid.extensions.toDateString
import com.wujie.kotlinforandroid.utils.Utilsjava
import com.wujie.kotlinforandroid.view.MyEditText
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
        val uri: Uri = Uri.parse("http://formqueryreport.v5.cmp/v/html/index" +
                ".html#dostatistics/2/-4863645372985228399?from=from")
        val string = uri.encodedQuery
        edit_text.setText(string)
        var from = uri.getQueryParameterNames().size
        edit_text.setText(from.toString())
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        MyEditText.setEditTextNoSoftInput(edit_text)
        enableHomeAsUp { onBackPressed() }
        async(UI) {
            val result = bg{ RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()}
            bindForecast(result.await())
        }

        Log.i("wujie", Utilsjava.isAppInstalled(this, "com.seeyon.cmp").toString())
        Log.i("wujie", Utilsjava.isAppInstalled(this, "com.wujie.kotlinforandroid").toString())
        Utilsjava.showJumpDialog(this, "http://m1.seeyon.com/jinge_moffice_9.2.9.apk")

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
