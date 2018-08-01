package com.wujie.kotlinforandroid.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wujie.kotlinforandroid.R
import com.wujie.kotlinforandroid.domain.model.Forecast
import com.wujie.kotlinforandroid.domain.model.ForecastList
import com.wujie.kotlinforandroid.extensions.toDateString
import com.wujie.kotlinforandroid.utils.ctx

/**
 * Created by wujie on 2018/8/1/001.
 */
class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }


    class ViewHolder(private val containerView: View, private val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(containerView){

        private val icon: ImageView = containerView.findViewById(R.id.icon)
        private val dateText: TextView = containerView.findViewById(R.id.dateText)
        private val descriptionText: TextView = containerView.findViewById(R.id.descriptionText)
        private val maxTemperature: TextView = containerView.findViewById(R.id.maxTemperature)
        private val minTemperature: TextView = containerView.findViewById(R.id.minTemperature)


        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(icon)
                dateText.text = date.toDateString()
                descriptionText.text = description
                maxTemperature.text = "{$high}º"
                minTemperature.text = "{$low}º"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }



}