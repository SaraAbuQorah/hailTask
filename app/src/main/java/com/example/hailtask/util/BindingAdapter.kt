package com.example.hailtask.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hailtask.R
import com.example.hailtask.data.model.Data
import com.example.hailtask.data.model.GetItems
import com.example.hailtask.data.model.Item
import com.example.hailtask.ui.items.ItemsAdapter
import com.example.hailtask.ui.items.ItemsViewModel

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
     textView: TextView,item:Item){
     item.let {
          textView.text = item.address
     }
}

@BindingAdapter("setItemsImage")
fun setItemsImage(
     imageView: ImageView,item:Item){
     item.let {
          Glide.with(imageView.context).load(item.image)
               .placeholder(R.drawable.haiil)
               .into(imageView)
     }
}



