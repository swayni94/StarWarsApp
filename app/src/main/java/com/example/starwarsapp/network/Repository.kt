package com.example.starwarsapp.network

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapp.network.models.Films
import com.example.starwarsapp.network.models.Planet
import com.example.starwarsapp.network.models.Results
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (
    private val api:ApiService
    ) {

    fun getFilmsList():MutableLiveData<List<Results>> {
        val filmDatas=MutableLiveData<List<Results>>()
        val dataslist: Call<Films> = api.getFilms()
        dataslist.enqueue(object : Callback<Films>{
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.isSuccessful){
                    filmDatas.postValue(response.body()!!.results)
                }
            }
            override fun onFailure(call: Call<Films>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return filmDatas
    }

    fun getFilm(url:String):MutableLiveData<Results>{
        val filmData = MutableLiveData<Results>()
        val datas:Call<Results> = api.getFilm(url)
        datas.enqueue(object : Callback<Results>{
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful){
                    filmData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return filmData
    }

    fun getPlanetsList(page:Int):Single<Planet>{
        return api.getPlanets(page)
    }
}
