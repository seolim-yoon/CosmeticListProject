package com.example.cosmeticlistproject.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitProductAPI {
    @GET("public.glowday.com/test/app/product.{page}.json")
    fun getProductsList(@Path("page")page: String) : Call<ProductsResult>

    @GET("public.glowday.com/test/app/recommend_product.json")
    fun getRecommendsList() : Call<RecommendResult>
}