package com.example.cosmeticlistproject.repository

import com.example.cosmeticlistproject.data.ProductsResult
import com.example.cosmeticlistproject.data.RetrofitBuilder
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {
    fun getResult(page: Int): Single<ProductsResult> =
        Single.create { emitter ->
            RetrofitBuilder.api
                .getProductsList(page.toString())
                .enqueue(object : Callback<ProductsResult> {
                    override fun onResponse(
                        call: Call<ProductsResult>,
                        response: Response<ProductsResult>
                    ) {
                        val productResponse = response.body()
                        productResponse?.let { emitter.onSuccess(it) }
                    }

                    override fun onFailure(call: Call<ProductsResult>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
            }
}