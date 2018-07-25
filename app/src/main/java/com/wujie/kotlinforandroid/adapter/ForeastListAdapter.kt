package com.wujie.kotlinforandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.wujie.kotlinforandroid.domain.ForecastList

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


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

    }
}