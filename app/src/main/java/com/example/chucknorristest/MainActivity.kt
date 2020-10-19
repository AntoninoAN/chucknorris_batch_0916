package com.example.chucknorristest

import android.content.Intent
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
        val v = layoutInflater.inflate(R.layout.activity_main, null)
        setContentView(v)


        val tablayout = tablayout
        tablayout.setupWithViewPager(viewpager)
        tablayout
            .setOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.d(TAG, "onTabReselected: ")
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
            .replace(R.id.viewpager, fragment)
            .commit()
    }
}


