package com.example.eskooly

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://vidyalay.saanvigs.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}