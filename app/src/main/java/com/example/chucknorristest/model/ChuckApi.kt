package com.example.chucknorristest.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//http://api.icndb.com/jokes/random/20
//http://api.icndb.com/jokes/random
// ?firstName=John&lastName=Doe
interface ChuckApi {
    @GET("jokes/random")
    fun getRandomJoke(): Call<ChuckResponse>
    @GET("jokes/random")
    fun getNamedJoke(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): Call<ChuckResponse>
    @GET("jokes/random/{pathvariable}")
    fun getListOfJokes(
        @Path("pathvariable") size: Int = 20
    )
}