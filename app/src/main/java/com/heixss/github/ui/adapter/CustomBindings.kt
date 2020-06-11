package com.heixss.github.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.getContext())
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}