package com.rubahapi.moviedb.model

import org.json.JSONObject

data class Weather(
    val id:Int?,
    val name:String?,
    val currentWeather:String,
    val description:String,
    val temperature:String
){
    constructor(objects: JSONObject): this(
            objects.getInt("id"),
            objects.getString("name"),
            objects.getJSONArray("weather").getJSONObject(0).getString("main"),
            objects.getJSONArray("weather").getJSONObject(0).getString("description"),
        ""
        )
}