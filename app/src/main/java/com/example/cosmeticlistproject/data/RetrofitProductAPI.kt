package com.example.cosmeticlistproject.data

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitProductAPI {
    @GET("public.glowday.com/test/app/product.1.json")
    fun getProductsList1(): Call<ProductsResult>

    @GET("public.glowday.com/test/app/product.2.json")
    fun getProductsList2(): Call<ProductsResult>

    @GET("public.glowday.com/test/app/product.3.json")
    fun getProductsList3(): Call<ProductsResult>
}