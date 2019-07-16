package com.rubahapi.moviedb.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.rubahapi.moviedb.api.TheMovieDbApi.getTvShowList
import com.rubahapi.moviedb.model.TvShow
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class TVShowViewModel:ViewModel(){
    val listTvShow:MutableLiveData<ArrayList<TvShow>> = MutableLiveData()

    fun setListTvShow(){
        val client = AsyncHttpClient()
        val listItem:ArrayList<TvShow> = ArrayList()

        client.get(getTvShowList(), object: AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                val responseObjects = JSONObject(result)
                val list = responseObjects.getJSONArray("results")

                for (i in 0 until list.length()){
                    val tvShow = TvShow(list.getJSONObject(i))
                    listItem.add(tvShow)
                }
                listTvShow.postValue(listItem)
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