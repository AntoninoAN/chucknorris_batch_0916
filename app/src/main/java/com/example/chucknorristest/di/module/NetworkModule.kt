package com.example.chucknorristest.di.module

import com.example.chucknorristest.model.ChuckApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideChuckApi(moshiConverterFactory:
                        MoshiConverterFactory): ChuckApi{
        return Retrofit.Builder()
            .baseUrl("http://api.icndb.com/")
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(ChuckApi::class.java)
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory{
        return MoshiConverterFactory.create()
    }


}



