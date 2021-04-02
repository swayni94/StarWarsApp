package com.example.starwarsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapp.network.Repository
import com.example.starwarsapp.network.models.Planet
import com.example.starwarsapp.network.models.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val disposable = CompositeDisposable()

    var planetPage = HashMap<Int,Planet>()
    private val _planetLiveData=MutableLiveData<HashMap<Int,Planet>>()
    var planetLiveData:LiveData<HashMap<Int,Planet>> = _planetLiveData
    var film:LiveData<Results>?= null

    fun fectFilmDatas(url: String, ){
        viewModelScope.launch {
            try {
                film = repository.getFilm(url)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun fectPlanets(pageIds:ArrayList<Int>){
        var i = 0;
        viewModelScope.launch {
            for (pageid in pageIds){
                if (i<pageid) {
                    try {
                        i = pageid
                        fectPlanet(pageid)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

     fun fectPlanet(page:Int){
        disposable.add(repository.getPlanetsList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Planet>(){
                    override fun onSuccess(t: Planet) {
                        planetPage.set(page, t)
                        _planetLiveData.postValue(planetPage)
                    }
                    override fun onError(e: Throwable) {
                        Log.e("hata","kontrol et")
                    }
                })
        )
     }
}