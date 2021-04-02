package com.example.starwarsapp.injection.module

import com.example.starwarsapp.helper.SearchHelper
import com.example.starwarsapp.network.ApiService
import com.example.starwarsapp.network.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        api: ApiService
    ):Repository{
        return Repository(api)
    }

    @Provides
    @Singleton
    fun provideSearchHelper():SearchHelper{
        return SearchHelper()
    }
}