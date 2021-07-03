package com.example.cosmeticlistproject.data

import com.example.cosmeticlistproject.ui.model.RecommendModel
import com.google.gson.annotations.SerializedName

data class RecommendResult(
    @SerializedName("recommend1")
    val recommend1: List<Recommend>,
    @SerializedName("recommend2")
    val recommend2: List<Recommend>,
    @SerializedName("recommend3")
    val recommend3: List<Recommend>
) {
    data class Recommend(
        @SerializedName("idProduct")
        val idProduct: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("productTitle")
        val productTitle: String,
        @SerializedName("ratingAvg")
        val ratingAvg: Double,
        @SerializedName("reviewCount")
        val reviewCount: String
    )
}

fun List<RecommendResult.Recommend>.transformRecommendModel() =
   this.map {
        RecommendModel(
            it.imageUrl, it.productTitle, it.ratingAvg, it.reviewCount
        )
    }

fun RecommendResult.transformRecommendModelList() : ArrayList<ArrayList<RecommendModel>> {
    return arrayListOf<ArrayList<RecommendModel>>().apply {
        add(ArrayList(this@transformRecommendModelList.recommend1.transformRecommendModel()))
        add(ArrayList(this@transformRecommendModelList.recommend2.transformRecommendModel()))
        add(ArrayList(this@transformRecommendModelList.recommend3.transformRecommendModel()))
    }
}
