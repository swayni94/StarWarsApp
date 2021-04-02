package com.example.starwarsapp

import android.app.Application
import com.example.starwarsapp.injection.component.AppComponent
import com.example.starwarsapp.injection.component.DaggerAppComponent
import com.example.starwarsapp.injection.module.AppModule


open class AppAplication : Application(){
    companion object{
        lateinit var appComponent:AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

      private fun initDagger(app: AppAplication): AppComponent =
          DaggerAppComponent.builder()
              .appModule(AppModule(app))
              .build()
}