package com.example.cosmeticlistproject.ui.adapter.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(ivThumbnail: ImageView, url: String) {
        Glide.with(ivThumbnail.context)
            .load(url)
            .centerCrop()
            .error(android.R.drawable.stat_notify_error)
            .timeout(500)
            .into(ivThumbnail)
    }
}