package com.example.chucknorristest.di.module

import com.example.chucknorristest.model.ChuckApi
import com.example.chucknorristest.model.NorrisRepository
import dagger.Module
import dagger.Provides

@Module
class NorrisRepositoryModule {
    @Provides
    fun provideNorrisRepository(api: ChuckApi)
            : NorrisRepository{
        return NorrisRepository(api)
    }
}