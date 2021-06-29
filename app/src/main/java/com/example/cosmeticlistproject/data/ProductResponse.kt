package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    var productsResult: ProductsResult?
)