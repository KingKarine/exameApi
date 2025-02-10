package com.karine.eventosapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitService(): ApiService {
    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}