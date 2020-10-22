package com.example.chucknorristest.model

import android.util.Log
import com.example.chucknorristest.viewmodel.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NorrisRepository"

class NorrisRepository(val api: ChuckApi) {

    fun randomJoke(callback: (data: AppState)-> Unit){
        api.getRandomJoke()
            .enqueue(object : Callback<ChuckResponse> {
                override fun onFailure(call: Call<ChuckResponse>,
                                       t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                    val appState = AppState.ERROR(
                        t.message ?: "Something Failed"
                    )
                    callback.invoke(appState)
                }

                override fun onResponse(
                    call: Call<ChuckResponse>,
                    response: Response<ChuckResponse>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ")
                        response.body()?.let{body->
                            val appState = AppState.RESPONSE(
                                body
                            )
                            callback.invoke(appState)
                        }
                    }
                }
            })
    }

    fun randomNamedJoke(callback: (data: AppState)-> Unit, input: String){

        val firstName = input.split(" ".toRegex())
        //input.substring(0,input.indexOf(' '))

        api.getNamedJoke(firstName[0], firstName[1])
            .enqueue(object : Callback<ChuckResponse>{
                override fun onFailure(call: Call<ChuckResponse>, t: Throwable) {
                    callback.invoke(
                        AppState.ERROR(t.message ?: "Error"))
                }

                override fun onResponse(
                    call: Call<ChuckResponse>,
                    response: Response<ChuckResponse>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.let {item->
                            callback.invoke(
                                AppState.RESPONSE(item))
                        }
                    }
                    //NorrisDB.createInstance(this).getDao().
                    //todo insert into DB
                    //todo query from DB to update singleJokeMutableLivedata
                }
            })
    }
}

// VM -> R  ||  R -> VM
// MVP
// IV -> P


// R -> M( db, api )
// M -> R
// R -> VM