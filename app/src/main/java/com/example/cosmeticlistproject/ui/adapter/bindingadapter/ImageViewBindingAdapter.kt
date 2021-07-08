package com.example.cosmeticlistproject.ui.adapter.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(ivThumbnail: ImageView, url: String) {

        val circularProgressDrawable = CircularProgressDrawable(ivThumbnail.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(ivThumbnail.context)
            .load(url)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .error(android.R.drawable.stat_notify_error)
            .transition(DrawableTransitionOptions().crossFade())
            .timeout(500)
            .into(ivThumbnail)
    }
}