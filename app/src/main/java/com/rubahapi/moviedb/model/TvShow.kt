package com.rubahapi.moviedb.model

import org.json.JSONObject

data class TvShow (
    val name: String?,
    val overview: String?,
    val poster_path: String?
){
    constructor(parcel: JSONObject) : this(
        parcel.getString("name"),
        parcel.getString("overview"),
        parcel.getString("poster_path")
    )
}