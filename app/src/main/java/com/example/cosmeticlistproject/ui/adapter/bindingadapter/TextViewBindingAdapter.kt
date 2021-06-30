package com.example.cosmeticlistproject.ui.adapter.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewBindingAdapter {
    @BindingAdapter("setReview")
    @JvmStatic
    fun setReview(tvReview: TextView, reviewCount: String) {
        tvReview.text = "(리뷰 $reviewCount)"
    }
}