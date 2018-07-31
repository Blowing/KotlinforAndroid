package com.wujie.kotlinforandroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.wujie.kotlinforandroid.adapter.ForeastListAdapter
import com.wujie.kotlinforandroid.request.RequestForecastCommand
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val foreastList = findViewById<RecyclerView>(R.id.forecast_list)
        foreastList.layoutManager = LinearLayoutManager(this)
        val list = listOf(1,2, 3,4)
        list.take(2)
        list.slice(listOf(1,3,4))
        assert(list.all { it % 2 == 0 })
        val a: Int? = null


        val myString = a?.toString() ?: throw IllegalStateException()



        async {
            val result = RequestForecastCommand("94043").exceute()
            uiThread {
                    val adapter = ForeastListAdapter(result) {
                        forecast ->  toast(forecast.date)
                    }
                foreastList.adapter = adapter

            }
        }

        val intent: Intent ?= null
        intent!!.setClass(MainActivity@this, Class.forName(MainActivity@this))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
