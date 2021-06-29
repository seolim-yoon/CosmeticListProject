package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName

data class ProductsResult(
    @SerializedName("products")
    var productsList: List<Products> = arrayListOf()
)