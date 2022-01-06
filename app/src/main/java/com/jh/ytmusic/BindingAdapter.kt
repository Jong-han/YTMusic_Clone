package com.jh.ytmusic

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun ImageView.setImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}