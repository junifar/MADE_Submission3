package com.rubahapi.moviedb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.rubahapi.moviedb.model.Weather
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class WeatherViewModel:ViewModel(){
    companion object{
        val API_KEY = "a9f98a74f1b580bd9a38bffd9968d08c"
    }

    val listWeather:MutableLiveData<ArrayList<Weather>> = MutableLiveData()

    fun setWeather(cities:String){
        val client = AsyncHttpClient()
        val listItems:ArrayList<Weather> = ArrayList()
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=$API_KEY"


        client.get(url, object:AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                val responseObject = JSONObject(result)
                val list = responseObject.getJSONArray("list")

                for (i in 0 until list.length()){
                    val weather = list.getJSONObject(i)
                    val weatherItems = Weather(weather)
                    listItems.add(weatherItems)
                }
                listWeather.postValue(listItems)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure:", error?.message)
            }

        })
    }
}