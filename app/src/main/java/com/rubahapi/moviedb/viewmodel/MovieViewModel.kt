package com.rubahapi.moviedb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.rubahapi.moviedb.api.TheMovieDbApi.getMovieList
import com.rubahapi.moviedb.model.Movie
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieViewModel:ViewModel(){
    val listMovies:MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    fun setListMovie(){
        val client = AsyncHttpClient()
        val listItem:ArrayList<Movie> = ArrayList()

        val url = getMovieList()

        client.get(getMovieList(), object: AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                val responseObjects = JSONObject(result)
                val list = responseObjects.getJSONArray("results")

                for (i in 0 until list.length()){
                    val movie = Movie(list.getJSONObject(i))
                    listItem.add(movie)
                }
                listMovies.postValue(listItem)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message)
            }

        })
    }

}