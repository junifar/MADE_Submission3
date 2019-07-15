package com.rubahapi.moviedb.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rubahapi.moviedb.R
import com.rubahapi.moviedb.model.Weather

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    val mData:ArrayList<Weather> = arrayListOf()

    fun setData(items: ArrayList<Weather>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Weather) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }


    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val textViewNamaKota: TextView? = itemView.findViewById(R.id.textKota)
        private val textViewTemperature: TextView? = itemView.findViewById(R.id.textTemp)
        private val textViewDescription: TextView? = itemView.findViewById(R.id.textDesc)

        fun bind(weather: Weather){
            textViewNamaKota?.text = weather.name
            textViewTemperature?.text = weather.temperature
            textViewDescription?.text = weather.description
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_weather, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return  mData.size
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])
    }

}