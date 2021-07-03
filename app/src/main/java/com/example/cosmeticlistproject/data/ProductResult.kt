package com.example.cosmeticlistproject.data

import com.example.cosmeticlistproject.ui.model.ProductModel
import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("products")
    val products: List<Product>
) {
    data class Product(
        @SerializedName("brand")
        val brand: Brand,
        @SerializedName("idBrand")
        val idBrand: Int,
        @SerializedName("idProduct")
        val idProduct: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("productRank")
        val productRank: String,
        @SerializedName("productScore")
        val productScore: Double,
        @SerializedName("productTitle")
        val productTitle: String,
        @SerializedName("rankChange")
        val rankChange: String,
        @SerializedName("rankChangeType")
        val rankChangeType: String,
        @SerializedName("ratingAvg")
        val ratingAvg: Double,
        @SerializedName("reviewCount")
        val reviewCount: String,
        @SerializedName("volume")
        val volume: String
    ) {
        data class Brand(
            @SerializedName("brandTitle")
            val brandTitle: String,
            @SerializedName("idBrand")
            val idBrand: Int,
            @SerializedName("imageUrl")
            val imageUrl: String
        )
    }
}

fun ProductResult.transformProductModel() =
    this.products.map { product ->
        ProductModel(
            product.imageUrl, product.productRank, product.productTitle, product.ratingAvg, product.reviewCount, product.brand.brandTitle
        )
    }
