package com.example.chucknorristest.model

import com.squareup.moshi.Json


//{
data class ChuckResponse(
    @Json(name="value")
    val value: JokeItem)

data class JokeItem (
    @Json(name = "id")
    val id: Int,
    @Json(name = "joke")
    val joke: String,
    @Json(name = "categories")
    val categories: List<String>
)

data class ChuckResponseList(
    @Json(name="value")
    val value: List<JokeItem>
)