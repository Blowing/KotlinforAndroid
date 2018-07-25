package com.wujie.kotlinforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.wujie.kotlinforandroid.adapter.ForeastListAdapter
import com.wujie.kotlinforandroid.request.RequestForecastCommand
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val foreastList = findViewById<RecyclerView>(R.id.forecast_list)
        foreastList.layoutManager = LinearLayoutManager(this)

        async {
            val result = RequestForecastCommand("94043").exceute()
            uiThread {
                foreastList.adapter = ForeastListAdapter(result)
            }
        }
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
