package com.example.chucknorristest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucknorristest.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NorrisViewModel"

class NorrisViewModel(val repo: NorrisRepository): ViewModel() {
    private val singleJokeMutableLivedata:
            MutableLiveData<AppState> = MutableLiveData()

    fun getSingleJokeLiveData(): LiveData<AppState>{
        return singleJokeMutableLivedata
    }

    fun getRandomJoke(){
        repo.randomJoke(::callbackRandomJoke)
    }

    fun callbackRandomJoke(data: AppState){
        singleJokeMutableLivedata.value = data
    }

    fun getNamedJoke(input: String){
       repo.randomNamedJoke(::callbackRandomJoke, input)
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