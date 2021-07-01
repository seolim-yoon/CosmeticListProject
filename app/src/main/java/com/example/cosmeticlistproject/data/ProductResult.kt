package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("products")
    var productList: ArrayList<Product> = arrayListOf()
)