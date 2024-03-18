package com.example.hailtask.util

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hailtask.R
import com.example.hailtask.data.model.GetItemDetails
import com.example.hailtask.data.model.ItemDetailClass
import com.example.hailtask.generated.callback.OnClickListener
import com.example.hailtask.ui.itemDetails.ItemDetailsFragmentDirections
import com.google.android.material.imageview.ShapeableImageView


@BindingAdapter("setItemDetName")
fun setItemDetName(
    textView: TextView, item: ItemDetailClass?
){
    item.let {
        textView.text = item?.name
    }
}

@BindingAdapter("setItemsDisc")
fun setItemsDisc(
    textView: TextView, item: ItemDetailClass?
){
    item.let {
        textView.text = item?.description
    }
}
@BindingAdapter("setItemDetAddress")
fun setItemDetAddress(
    textView: TextView,item: ItemDetailClass?
){
    item.let {
        textView.text = item?.address
    }
}




@BindingAdapter("setItemsTimeOpen")
fun setItemsTimeOpen(
    textView: TextView,item: ItemDetailClass?
){
    item.let {
        textView.text = item?.open_time
    }
}


@BindingAdapter("setItemsTimeClose")
fun setItemsTimeClose(
    textView: TextView,item: ItemDetailClass?
){
    item.let {
        textView.text = item?.close_time
    }
}
@BindingAdapter("setItemscall")
fun setItemscall(linearLayout: LinearLayout, item: ItemDetailClass?) {

    linearLayout.setOnClickListener {
        val context = linearLayout.context

        if (!item?.phone.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${item?.phone}")
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {

            }
        } else {
            Toast.makeText(context, "There is no phone number ", Toast.LENGTH_SHORT).show()

        }
    }

}
@BindingAdapter("setItemsGmail")
fun setItemsGmail(linearLayout: LinearLayout, item: ItemDetailClass?) {
    val context = linearLayout.context

    linearLayout.setOnClickListener {
        if (!item?.email?.equals(null)!!) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${item?.email}")
            }
            if (intent.resolveActivity(linearLayout.context.packageManager) != null) {
                linearLayout.context.startActivity(intent)
            } else {

            }
        } else {
            Toast.makeText(context, "There is no Email ", Toast.LENGTH_SHORT).show()

        }
    }
}


@BindingAdapter("setItemsDirection")
fun setItemsDirection(
    linearLayout: LinearLayout,item: ItemDetailClass?
) {
    val context = linearLayout.context

    linearLayout.setOnClickListener {
        if (item?.lat != null && item?.lng != null) {
            val uri = "geo:${item.lat},${item.lng}?z=15"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            if (mapIntent.resolveActivity(linearLayout.context.packageManager) != null) {
                linearLayout.context.startActivity(mapIntent)
            } else {
                linearLayout.visibility=View.GONE

            }
        } else {
            Toast.makeText(context, "There is no location ", Toast.LENGTH_SHORT).show()
        }
    }
}
@BindingAdapter("setItemsWebsite")
fun setItemsWebsite(linearLayout: LinearLayout, item: ItemDetailClass?) {
    val context = linearLayout.context

    linearLayout.setOnClickListener {
        // Check if the website URL is available
        if (!item?.link.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(item?.link)
            }
            if (intent.resolveActivity(linearLayout.context.packageManager) != null) {
                linearLayout.context.startActivity(intent)
            } else {

            }
        } else {
            Toast.makeText(context, "There is no website ", Toast.LENGTH_SHORT).show()

        }
    }
}

@BindingAdapter("setItemDetButton")
fun setItemDetButton(imageView: AppCompatImageView, item: ItemDetailClass?) {
    item.let {
        val imageResource = if (item?.is_favorite == 0) {
            R.drawable.ic_shortcut_bookmark_borderz
        } else {
            R.drawable.ic_shortcut_bookmark
        }

        imageView.setImageResource(imageResource)
    }
}

@BindingAdapter("setItemOnClickArrowBack")
fun setItemOnClickArrowBack(v: View,clickListener: View.OnClickListener?) {
    v.setOnClickListener {
        val navController = v.findNavController()
        navController.popBackStack(R.id.itemDetailsFragment, false)
    }
}
@BindingAdapter("setItemOnClickArrowBack1")
fun setItemOnClickArrowBack1(v: View,clickListener: View.OnClickListener?) {
    v.setOnClickListener {
        val navController = v.findNavController()
        navController.popBackStack(R.id.firstFragment, false)
    }
}