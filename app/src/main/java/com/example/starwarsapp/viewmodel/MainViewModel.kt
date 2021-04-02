package com.example.starwarsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.helper.SearchHelper
import com.example.starwarsapp.network.Repository
import com.example.starwarsapp.network.models.Results
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository,
                                        private val searchHelper: SearchHelper) : ViewModel() {

    var filmsData : LiveData<List<Results>>? = null
    var searchFilms : LiveData<List<Results>>? = null

    fun fecthFilmsDatas(){
        viewModelScope.launch{
            try {
                filmsData = repository.getFilmsList()
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


    fun setSearchQuery(search:String){
        val films = filmsData?.value.orEmpty()
        searchFilms = searchHelper.searchGetFilms(search, films)
    }
}