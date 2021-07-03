package com.example.cosmeticlistproject.ui.model

data class ProductModel(
    val imageUrl: String? = null,
    val productRank: String = "",
    val productTitle: String = "",
    val ratingAvg: Double = 0.0,
    val reviewCount: String = "",
    val brandTitle: String = ""
) : BaseModel

