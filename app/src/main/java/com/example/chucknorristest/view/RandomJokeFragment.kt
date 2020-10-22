package com.example.chucknorristest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorristest.NorrisApplication
import com.example.chucknorristest.R
import com.example.chucknorristest.viewmodel.AppState
import com.example.chucknorristest.viewmodel.NorrisViewModel
import com.example.chucknorristest.viewmodel.NorrisViewModelProvider
import kotlinx.android.synthetic.main.random_fragment.view.*
import javax.inject.Inject

class RandomJokeFragment: Fragment() {

    companion object{
        fun createRandomJokeFragment(): RandomJokeFragment{
            return RandomJokeFragment()
        }
    }

    lateinit var name: String

    private lateinit var norrisViewModel: NorrisViewModel
    @Inject
    lateinit var norrisProvider: NorrisViewModelProvider

//    private val norrisViewModel: //Lazy<NorrisViewModel>
//            //NorrisViewMOdel
//            NorrisViewModel by lazy {
//        ViewModelProvider(
//            this,
//            object : ViewModelProvider.Factory{
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return NorrisViewModel() as T
//                }
//            }
//        ).get(NorrisViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NorrisApplication.getComponent().injectRandomJoke(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.random_fragment,
            container,
            false)

        norrisViewModel = norrisProvider
            .create(NorrisViewModel::class.java)


        norrisViewModel.getSingleJokeLiveData().observe(
            this,
            object : Observer<AppState>{
                override fun onChanged(t: AppState?) {
                    t?.let {
                        when(t){
                            is AppState.RESPONSE ->{
                                val joke = t.chuckResponse.value.joke
                                view.tv_random_joke.text = joke
                            }
                        }
                    }
                }
            }
        )
        norrisViewModel.getRandomJoke()
        return view
    }

}