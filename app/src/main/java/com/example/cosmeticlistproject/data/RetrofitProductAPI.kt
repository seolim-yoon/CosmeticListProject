package com.example.cosmeticlistproject.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitProductAPI {
    @GET("public.glowday.com/test/app/product.{page}.json")
    fun getProductList(@Path("page")page: String) : Single<ProductResult>

    @GET("public.glowday.com/test/app/recommend_product.json")
    fun getRecommendList() : Single<RecommendResult>
}