package com.example.chucknorristest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.chucknorristest.view.RandomFilterJokeFragment
import com.example.chucknorristest.view.RandomJokeFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),
    TabLayout.OnTabSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val v = layoutInflater
            .inflate(R.layout.activity_main,
            null)
        setContentView(v)


        val tablayout = tablayout
        tablayout.setupWithViewPager(viewpager)
        tablayout
            .setOnTabSelectedListener(this)

        createSharedPreferences()
        readingSharedPreferences()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.d(TAG, "onTabReselected: ")
        tab?.let {currentTab->
            inflateCurrentFragment(currentTab.position)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.d(TAG, "onTabUnselected: ")
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        Log.d(TAG, "onTabSelected: ${tab?.text}")
        tab?.let {currentTab->
            when(currentTab.position){

                0->{
                    inflateCurrentFragment(currentTab.position)
                }
                1->{
                    inflateCurrentFragment(currentTab.position)
                }
                2->{
                    inflateCurrentFragment(currentTab.position)
                }

                else-> throw Exception("Not supported Tab")
            }
        }
    }

    private fun inflateCurrentFragment(position: Int) {
        val fragment = when(position){
            0-> RandomJokeFragment.createRandomJokeFragment()
            1-> RandomFilterJokeFragment.createRandomFilterJokeFragment()
            else-> throw Error("Fragment not found")
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    //Create a SharedPreferences
    // Local Storage {SharedPreferences, Databases, Files}
    // Public Storage {SD Card, Gallery, Documents}
    fun createSharedPreferences(){
        val sharedPreferences = getSharedPreferences(
            "my_shared_preferences",
            Context.MODE_PRIVATE)
        val editorSharedPreferences = sharedPreferences.edit()
        editorSharedPreferences.putString("zipcode", "30067")
        editorSharedPreferences.putString("units", "celsius")
        editorSharedPreferences.commit()
    }

    fun readingSharedPreferences(){
        val sharedPreferences = getSharedPreferences(
            "my_shared_preferences",
            Context.MODE_PRIVATE)
        val getZipcode =
            sharedPreferences.getString("zipcode", "N/A")
        val getUnits =
            sharedPreferences.getString("units", "N/A")

        //if(getZipcode.equals("N/A") && getUnits.equals("N/A"))
            //showSettingsActivity()
    }




}


