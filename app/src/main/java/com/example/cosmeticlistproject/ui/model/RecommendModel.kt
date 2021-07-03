package com.example.cosmeticlistproject.ui.model

data class RecommendModel(
    val imageUrl: String? = null,
    val productTitle: String = "",
    val ratingAvg: Double = 0.0,
    val reviewCount: String = ""
) : BaseModel