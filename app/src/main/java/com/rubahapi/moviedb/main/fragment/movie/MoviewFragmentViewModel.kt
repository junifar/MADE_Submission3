package com.rubahapi.moviedb.main.fragment.movie

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rubahapi.moviedb.model.Movie

class MoviewFragmentViewModel: ViewModel(){

    private val items: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadMovie()
        }
    }

    fun getMovies(): LiveData<List<Movie>>{
        return items
    }

    private fun loadMovie(){

    }

}