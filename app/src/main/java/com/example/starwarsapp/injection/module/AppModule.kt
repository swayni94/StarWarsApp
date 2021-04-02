package com.example.starwarsapp.injection.module

import android.app.Application
import android.content.Context
import com.example.starwarsapp.injection.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule (private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }
}