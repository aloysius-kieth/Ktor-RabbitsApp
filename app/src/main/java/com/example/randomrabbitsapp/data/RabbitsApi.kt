package com.example.randomrabbitsapp.data

import retrofit2.http.GET

interface RabbitsApi {

    companion object {
        const val BASE_URL = "http://192.168.1.154:8100"
    }

    @GET("/randomrabbit")
    suspend fun getRandomRabbit(): Rabbit
}