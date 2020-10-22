package com.example.chucknorristest.di.component

import com.example.chucknorristest.di.module.NetworkModule
import com.example.chucknorristest.di.module.NorrisApplicationModule
import com.example.chucknorristest.di.module.NorrisRepositoryModule
import com.example.chucknorristest.di.module.NorrisViewModelModule
import com.example.chucknorristest.view.RandomFilterJokeFragment
import com.example.chucknorristest.view.RandomJokeFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class,
NorrisApplicationModule::class,
NorrisRepositoryModule::class,
NorrisViewModelModule::class])
@Singleton
interface NorrisComponent {
    fun injectRandomJoke(fragment: RandomJokeFragment)
    fun injectFilterJoke(fragment: RandomFilterJokeFragment)
    //fun injectEndlessJoke(fragment: )
}