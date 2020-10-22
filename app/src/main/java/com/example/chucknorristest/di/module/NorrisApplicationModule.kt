package com.example.chucknorristest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class NorrisApplicationModule(val context: Context) {

    @Provides
    fun provideApplicationContext() : Context{
        return context
    }
}