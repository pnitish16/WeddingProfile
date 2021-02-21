package com.demoapp.weddingprofile.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("title", "first", "last", requireAll = false)
fun loadFullName(view: TextView, title: String?, first: String?, last: String?) {
    view.text = "$title. $first $last"
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions())
        .into(view)
}

@BindingAdapter("isResponsed")
fun isResponsed(view : ImageView, isResponsed: Boolean){
    if(isResponsed){
        view.visibility = View.GONE
    }else{
        view.visibility = View.VISIBLE
    }
}