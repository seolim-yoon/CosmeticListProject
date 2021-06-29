package com.example.cosmeticlistproject.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProductAPI {
    @GET("public.glowday.com/test/app/product.1.json")
    fun getProductsList(
//        @Query("")
    ): Call<ProductResponse>
}