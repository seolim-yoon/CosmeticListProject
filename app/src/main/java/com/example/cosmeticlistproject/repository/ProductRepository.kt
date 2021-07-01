package com.example.cosmeticlistproject.repository

import com.example.cosmeticlistproject.data.ProductResult
import com.example.cosmeticlistproject.data.RecommendResult
import com.example.cosmeticlistproject.data.RetrofitBuilder
import io.reactivex.Single

class ProductRepository {
    fun getProductResult(page: Int): Single<ProductResult> = RetrofitBuilder.api.getProductList(page.toString())

    fun getRecommendResult(): Single<RecommendResult> = RetrofitBuilder.api.getRecommendList()
}