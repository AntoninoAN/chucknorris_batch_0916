package com.example.chucknorristest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorristest.model.ChuckResponse
import com.example.chucknorristest.model.ChuckResponseList
import com.example.chucknorristest.model.Network
import com.example.chucknorristest.model.NorrisDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NorrisViewModel"
class NorrisViewModel: ViewModel() {
    private val singleJokeMutableLivedata:
            MutableLiveData<AppState> = MutableLiveData()

    fun getSingleJokeLiveData(): LiveData<AppState>{
        return singleJokeMutableLivedata
    }

    fun getRandomJoke(){
        val network = Network.initRetrofit()
        network.getRandomJoke()
            .enqueue(object : Callback<ChuckResponse>{
                override fun onFailure(call: Call<ChuckResponse>,
                                       t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                    val appState = AppState.ERROR(
                        t.message ?: "Something Failed"
                    )
                    singleJokeMutableLivedata.value = appState
                }

                override fun onResponse(
                    call: Call<ChuckResponse>,
                    response: Response<ChuckResponse>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ")
                        response.body()?.let{body->
                            val appState = AppState.RESPONSE(
                                body
                            )
                            singleJokeMutableLivedata.value = appState
                        }
                    }
                }
            })
    }

    fun getNamedJoke(input: String){
        //todo splitData()
        val firstName = input.split(" ".toRegex())
        //input.substring(0,input.indexOf(' '))

        Network.initRetrofit()
            .getNamedJoke(input,input)
            .enqueue(object : Callback<ChuckResponse>{
                override fun onFailure(call: Call<ChuckResponse>, t: Throwable) {
                    singleJokeMutableLivedata.value =
                        AppState.ERROR(t.message ?: "Error")
                }

                override fun onResponse(
                    call: Call<ChuckResponse>,
                    response: Response<ChuckResponse>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.let {item->
                            singleJokeMutableLivedata.value =
                                AppState.RESPONSE(item)
                        }
                    }
                    //NorrisDB.createInstance(this).getDao().
                    //todo insert into DB
                    //todo query from DB to update singleJokeMutableLivedata
                }
            })
    }

    fun updateNeverEndingList(){}
}

sealed class AppState{
    object LOADING: AppState()
    data class ERROR(val errorMessage: String): AppState()
    data class RESPONSE(val chuckResponse: ChuckResponse): AppState()
    data class RESPONSELIST(val chuckList: ChuckResponseList): AppState()
}

//1.- Subclass ViewModel
//2.- private MutableLivedata
//3.- public getter LiveData