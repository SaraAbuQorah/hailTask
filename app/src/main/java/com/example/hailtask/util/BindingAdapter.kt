package com.example.hailtask.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.hailtask.R
import com.example.hailtask.data.model.itemss.Item

@BindingAdapter("setItemsName")
fun setItemsName(
     textView: TextView,item: Item
){
     item.let {
          textView.text = item.name
     }
}

@BindingAdapter("setItemsAddress")
fun setItemsAddress(
     textView: TextView,item: Item
){
     item.let {
          textView.text = item.address
     }
}

@BindingAdapter("setItemsImage")
fun setItemsImage(
     imageView: ImageView,item: Item
){
     item.let {
          val baseUrl = "https://hail.website"
          val imageUrl = baseUrl + item.image
          Glide.with(imageView.context).load(imageUrl)
               .placeholder(R.drawable.haiil)
               .into(imageView)
     }
}


@BindingAdapter("setItemsButton")
fun setItemsButton(
     imageView: ImageView,item: Item
){
     item.let {
          val imageResource = if (item.is_favorite == 0) {
               R.drawable.ic_shortcut_bookmark_borderz
          } else {
               R.drawable.ic_shortcut_bookmark
          }

          imageView.setImageResource(imageResource)
     }
}



