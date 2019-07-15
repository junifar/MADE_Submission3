package com.rubahapi.moviedb

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.EditText
import com.rubahapi.moviedb.adapter.WeatherAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Button
import com.rubahapi.moviedb.viewmodel.WeatherViewModel
import android.arch.lifecycle.ViewModelProviders
import com.rubahapi.moviedb.model.Weather


class WeatherActivity : AppCompatActivity() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var edtCity: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherViewModel.listWeather.observe(this, getWeather)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        edtCity = findViewById(R.id.editCity)
        progressBar = findViewById(R.id.progressBar)

        findViewById<Button>(R.id.btnCity).setOnClickListener(myListener)
    }

    private val getWeather:Observer<ArrayList<Weather>> =
        Observer { weatherItems ->
            if (weatherItems != null) {
                adapter.setData(weatherItems)
                showLoading(false)
            }
        }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private val myListener = object: View.OnClickListener {
        override fun onClick(v: View?) {
            val city = edtCity.text.toString()
            if (TextUtils.isEmpty(city)) return
            weatherViewModel.setWeather(city)
            showLoading(true)
        }
    }
}
