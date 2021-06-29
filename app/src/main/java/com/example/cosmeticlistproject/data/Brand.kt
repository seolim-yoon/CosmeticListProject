package com.example.cosmeticlistproject.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Brand(
    @SerializedName("brandTitle")
    var brandTitle: String
) : Serializable {
}