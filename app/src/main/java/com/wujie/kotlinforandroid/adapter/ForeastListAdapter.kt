package com.wujie.kotlinforandroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by wujie on 2018/7/24/024.
 */
class ForeastListAdapter(val itmes: List<String>) :
        RecyclerView.Adapter<ForeastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun getItemCount(): Int {
        return itmes.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = itmes[position]
    }


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

    }
}