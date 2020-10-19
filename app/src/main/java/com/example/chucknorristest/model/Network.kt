package com.example.chucknorristest.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Network private constructor() {
    companion object{
        private var INSTANCE: Network? = null
        private var INSTANCEAPI: ChuckApi? = null //Nothing?

        fun getInstance(): Network{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            else{
                val instance = Network()
                INSTANCE = instance
                return instance
            }
        }

        fun initRetrofit(): ChuckApi{
            val tempApi = INSTANCEAPI
            if(tempApi != null) return tempApi
            else{
                val instance = Retrofit.Builder()
                    .baseUrl("http://api.icndb.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(ChuckApi::class.java)
                INSTANCEAPI = instance
                return instance
            }
        }
    }
}
