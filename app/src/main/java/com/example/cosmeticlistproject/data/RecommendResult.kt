package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName

data class RecommendResult(
    @SerializedName("recommend1")
    var recommendList1: ArrayList<Product> = arrayListOf(),
    @SerializedName("recommend2")
    var recommendList2: ArrayList<Product> = arrayListOf(),
    @SerializedName("recommend3")
    var recommendList3: ArrayList<Product> = arrayListOf()
)