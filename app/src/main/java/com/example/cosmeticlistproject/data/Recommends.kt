package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recommends(
    @SerializedName("productTitle")
    var productTitle: String,
    @SerializedName("ratingAvg")
    var ratingAvg: String,
    @SerializedName("reviewCount")
    var reviewCount: String,
    @SerializedName("imageUrl")
    var imageUrl: String
) : Serializable {
}