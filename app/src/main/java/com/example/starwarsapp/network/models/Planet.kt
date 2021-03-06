package com.example.starwarsapp.network.models

import com.google.gson.annotations.SerializedName

data class Planet (
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<PlanetResult>
    )