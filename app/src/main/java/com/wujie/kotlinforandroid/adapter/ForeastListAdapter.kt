package com.wujie.kotlinforandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.domain.Forecast
import com.wujie.kotlinforandroid.domain.ForecastList

import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find

/**
 * Created by wujie on 2018/7/24/024.
 */
class ForeastListAdapter(val itmes: ForecastList) :
        RecyclerView.Adapter<ForeastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun getItemCount(): Int {
        return itmes.dailyForecast.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(itmes.dailyForecast[position]) {
            holder?.textView?.text = "$date -$description -$high/$low"
        }
    }


    class ViewHolder(view: View, val itemClick: OnItemClickListener) :
            RecyclerView.ViewHolder(view) {
        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {

            }
        }
    }

    public interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}