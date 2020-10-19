package com.example.chucknorristest.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface NorrisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewEntry(input: ChuckEntity)

    @Query("SELECT * FROM joke_table")
    fun getEntries(): List<ChuckEntity>
}
//Dependencies (Room, Room-Compiler)
//@Dao | @Entity | @Room
//Create the Dao (Data Access Object)
//Interface (SQL commands) CRUD
//Create the Tables (Entity)
//Create the abstract Room class

