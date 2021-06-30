package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName

data class RecommendResult(
    @SerializedName("recommend1")
    var recommendList1: ArrayList<Products> = arrayListOf(),
    @SerializedName("recommend2")
    var recommendList2: ArrayList<Products> = arrayListOf(),
    @SerializedName("recommend3")
    var recommendList3: ArrayList<Products> = arrayListOf()
)