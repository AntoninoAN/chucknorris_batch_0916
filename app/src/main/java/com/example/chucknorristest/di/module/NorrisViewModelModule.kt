package com.example.chucknorristest.di.module

import com.example.chucknorristest.model.NorrisRepository
import com.example.chucknorristest.viewmodel.NorrisViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class NorrisViewModelModule {

    @Provides
    fun provideViewModel(repo: NorrisRepository): NorrisViewModelProvider {
        return NorrisViewModelProvider(repo)
    }
}