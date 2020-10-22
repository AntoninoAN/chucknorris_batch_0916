package com.example.chucknorristest

import android.app.Application
import com.example.chucknorristest.di.component.DaggerNorrisComponent
import com.example.chucknorristest.di.component.NorrisComponent
import com.example.chucknorristest.di.module.NetworkModule
import com.example.chucknorristest.di.module.NorrisApplicationModule
import com.example.chucknorristest.di.module.NorrisRepositoryModule
import com.example.chucknorristest.di.module.NorrisViewModelModule

class NorrisApplication: Application(){
    //dagger
    //provide a Static Application Context
    //load a library at the beginning (Timber)
    //preloading operations.

    companion object{
        private lateinit var daggerComponent: NorrisComponent

        fun getComponent() = daggerComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerNorrisComponent.builder()
            .networkModule(NetworkModule())
            .norrisRepositoryModule(NorrisRepositoryModule())
            .norrisViewModelModule(NorrisViewModelModule())
            .norrisApplicationModule(NorrisApplicationModule(this))
            .build()
    }

}



