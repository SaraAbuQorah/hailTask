package com.example.hailtask.ui.itemDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.hailtask.R


class pagerAdapter(private val context: Context, private val items: List<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemList = items[position]

        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.image, container, false)
        val baseUrl = "https://hail.website"

        val imageView: ImageView = itemView.findViewById<View>(R.id.ItemImg) as ImageView
        var imageUri: String = items[position]
        imageUri=baseUrl+imageUri


        imageUri.let {
            Glide.with(context).load(imageUri.toUri()).into(imageView)
        }


        itemView.setOnClickListener {
            val action2 = ItemDetailsFragmentDirections.actionItemDetailsFragmentToImageFragment2(imageUri)
           itemView.findNavController().navigate(action2)
        }

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return items.size
    }
}