package com.example.chucknorristest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorristest.R
import com.example.chucknorristest.viewmodel.AppState
import com.example.chucknorristest.viewmodel.NorrisViewModel
import kotlinx.android.synthetic.main.filter_fragment.view.*

class RandomFilterJokeFragment: Fragment() {

    companion object{
        fun createRandomFilterJokeFragment()
                = RandomFilterJokeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater,
            container,
            savedInstanceState)
        val view = inflater.inflate(
            R.layout.filter_fragment,
            container,
            false
        )
        val norrisViewModel = ViewModelProvider(this,
            object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return NorrisViewModel() as T
                }
            }).get(NorrisViewModel::class.java)

        norrisViewModel.getSingleJokeLiveData().observe(this,
        Observer {appState ->
            when(appState){
                is AppState.RESPONSE ->{
                    view.tv_filter_joke.text =
                        appState.chuckResponse.value.joke
                }
                is AppState.ERROR ->{
                    view.tv_filter_joke.text =
                        appState.errorMessage
                }
            }
        })

        norrisViewModel
            .getNamedJoke(view.til_first_name.editText!!.text.toString())

        return view
    }
}

