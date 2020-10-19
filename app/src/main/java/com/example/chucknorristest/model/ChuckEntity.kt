package com.example.chucknorristest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Check if device connected
//If True
//  get data, and save into DB
//If False
//  get data from DB (up to 20 entries)

@Entity(tableName = "joke_table")
data class ChuckEntity(
    @PrimaryKey(autoGenerate = true)
    val identifier: Int,
    @ColumnInfo(name = "joke_entry")
    val joke: String
)
//For insert
// val chuckEntity = ChuckEntity(0, "kdkdkdkd")










