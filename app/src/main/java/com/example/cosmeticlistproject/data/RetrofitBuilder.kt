package com.example.cosmeticlistproject.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val BASE_URL: String = "https://s3.ap-northeast-2.amazonaws.com"
    var api: RetrofitProductAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitProductAPI::class.java)
    }

}