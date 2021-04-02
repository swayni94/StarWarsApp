package com.example.starwarsapp.injection.component

import android.content.Context
import com.example.starwarsapp.MainActivity
import com.example.starwarsapp.fragment.DetailFragment
import com.example.starwarsapp.fragment.MainFragment
import com.example.starwarsapp.injection.module.AppModule
import com.example.starwarsapp.injection.module.CoroutinesModule
import com.example.starwarsapp.injection.module.NetworkModule
import com.example.starwarsapp.injection.module.RepositoryModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CoroutinesModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
interface AppComponent {

    fun context(): Context
    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)
}