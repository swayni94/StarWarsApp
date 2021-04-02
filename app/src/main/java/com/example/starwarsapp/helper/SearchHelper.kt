package com.example.starwarsapp.helper

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapp.network.models.Results
import java.util.ArrayList

class SearchHelper {
    fun searchGetFilms(search:String, films:List<Results>):MutableLiveData<List<Results>>{
        val result=MutableLiveData<List<Results>>()
        val items =ArrayList<Results>()
        films.forEach {
            if (it.title.contains(search,true)){
                items.add(it)
            }
        }
        result.value = items
        return result
    }
}