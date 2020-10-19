package com.example.chucknorristest.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChuckEntity::class], version = 1)
abstract class NorrisDB: RoomDatabase() {

    abstract fun getDao(): NorrisDao

    companion object{
       @Volatile
       private var INSTANCE: NorrisDB? = null

        fun createInstance(context: Context): NorrisDB{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            else{
             synchronized(this){
                    if(tempInstance != null) return tempInstance
                    else{
                        val instance = Room.databaseBuilder(
                            context,
                            NorrisDB::class.java,
                            "norris_db"
                        ).build()
                        INSTANCE = instance
                        return instance
                    }
                }
            }
        }
    }
}

