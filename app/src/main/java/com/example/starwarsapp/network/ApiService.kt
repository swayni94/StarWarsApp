package com.example.starwarsapp.network

import com.example.starwarsapp.network.models.Films
import com.example.starwarsapp.network.models.Planet
import com.example.starwarsapp.network.models.Results
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("films")
    fun getFilms() : Call<Films>

    @GET("planets/")
    fun getPlanets(@Query("page") id:Int) : Single<Planet>

    @GET("{film}")
    fun getFilm(@Path(value="film", encoded = false) url:String) : Call<Results>
}