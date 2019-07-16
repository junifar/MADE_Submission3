package com.rubahapi.moviedb.model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class Movie (
    val title: String?,
    val overview: String?,
    val poster_path: String?
){
    constructor(parcel: JSONObject) : this(
        parcel.getString("title"),
        parcel.getString("overview"),
        parcel.getString("poster_path")
    )
}